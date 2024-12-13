package br.com.foodwise.foodwise.service.exceptions;

import br.com.foodwise.foodwise.controllers.handlers.BusinessException;
import org.springframework.http.HttpStatus;

public class ResourceNotFound extends BusinessException {
    public ResourceNotFound(String message) {
        super("error-2", HttpStatus.NOT_FOUND, message);
    }
}