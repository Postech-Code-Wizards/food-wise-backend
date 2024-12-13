package br.com.foodwise.foodwise.controllers.handlers;

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
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex, Locale locale) {
        final String errorCode = ex.getCode();
        final HttpStatus status = ex.getStatus();
        final String message = ex.getMessage();

        String defaultMessage = apiErrorMessageSource.getMessage(errorCode, null, locale);

        String finalMessage = (message != null && !message.isEmpty() ? message + " " : "") + defaultMessage;

        final ErrorResponse errorResponse = ErrorResponse.of(status, new ErrorResponse.ApiError(errorCode, finalMessage));
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
