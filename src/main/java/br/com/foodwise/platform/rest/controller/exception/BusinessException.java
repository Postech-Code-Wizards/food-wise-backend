package br.com.foodwise.platform.rest.controller.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException {

    private final String code;
    private final HttpStatus status;
    private final String message;

}
