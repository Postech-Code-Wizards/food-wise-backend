package br.com.foodwise.platform.service;

import br.com.foodwise.platform.model.entities.User;
import br.com.foodwise.platform.rest.controller.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    public static void validateUserIsActive(Authentication auth) {
        var user = (User) auth.getPrincipal();
        if (Objects.nonNull(user.getDeletedAt())) {
            throw new BusinessException("USER_DOES_NOT_EXIST", HttpStatus.NOT_FOUND, "");
        }
    }

}