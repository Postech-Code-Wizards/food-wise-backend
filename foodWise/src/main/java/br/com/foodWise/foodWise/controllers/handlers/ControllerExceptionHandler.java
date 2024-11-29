package br.com.foodWise.foodWise.controllers.handlers;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final String NO_MESSSAGE_AVAILABLE = "No message available";

    private final MessageSource apiErrorMessageSource;

    public ControllerExceptionHandler(MessageSource apiErrorMessageSource) {
        this.apiErrorMessageSource = apiErrorMessageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e, Locale locale) {
        Stream<ObjectError> errors = e.getBindingResult().getAllErrors().stream();
        List<ErrorResponse.ApiError> apiErrors = errors
                .map(ObjectError::getDefaultMessage)
                .map(code -> toApiError(code, locale))
                .collect(toList());
        return ResponseEntity.badRequest().body(ErrorResponse.of(HttpStatus.BAD_REQUEST, apiErrors));
    }

    @ExceptionHandler({ BusinessException.class })
    public ResponseEntity<ErrorResponse> handleNotFoundException(BusinessException ex, Locale locale) {
        final String errorCode = ex.getCode();
        final HttpStatus status = ex.getStatus();

        final ErrorResponse errorResponse = ErrorResponse.of(status, toApiError(errorCode, locale));
        return ResponseEntity.status(status).body(errorResponse);
    }

    public ErrorResponse.ApiError toApiError(String errorCode, Locale locale, Object... args) {
        String message;
        try {
            message = apiErrorMessageSource.getMessage(errorCode, args, locale);
        } catch (NoSuchMessageException e) {
            message = NO_MESSSAGE_AVAILABLE;
        }
        return new ErrorResponse.ApiError(errorCode, message);
    }
}
