package br.com.foodWise.foodWise.service.exceptions.user;

import br.com.foodWise.foodWise.controllers.handlers.BusinessException;
import org.springframework.http.HttpStatus;

public class UserNotFound extends BusinessException {
    public UserNotFound() {
        super("error-2", HttpStatus.NOT_FOUND);
    }
}
