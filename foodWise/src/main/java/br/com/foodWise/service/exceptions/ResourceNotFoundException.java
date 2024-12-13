package br.com.foodWise.service.exceptions;

import br.com.foodWise.controllers.handlers.BusinessException;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException(String message) {
        super("error-2", HttpStatus.NOT_FOUND, message);
    }
}