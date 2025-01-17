package br.com.foodwise.platform.rest.controller.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException(String message) {
        super("NOT_FOUND", HttpStatus.NOT_FOUND, message);
    }
    public ResourceNotFoundException(String code, String message){
        super(code, HttpStatus.NOT_FOUND, message);
    }
}
