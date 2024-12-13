package br.com.foodWise.rest.controller.exception;

import br.com.foodWise.rest.controller.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException(String message) {
        super("error-2", HttpStatus.NOT_FOUND, message);
    }
}