package br.com.foodwise.rest.controller.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException(String message) {
        super("error-2", HttpStatus.NOT_FOUND, message);
    }
}