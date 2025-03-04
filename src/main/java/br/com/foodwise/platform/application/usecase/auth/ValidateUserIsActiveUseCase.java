package br.com.foodwise.platform.application.usecase.auth;

import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ValidateUserIsActiveUseCase {

    private ValidateUserIsActiveUseCase() {
    }

    public static void execute(Authentication auth) {
        var user = (UserEntity) auth.getPrincipal();
        if (Objects.nonNull(user.getDeletedAt())) {
            throw new BusinessException("USER_DOES_NOT_EXIST", HttpStatus.NOT_FOUND, "");
        }
    }

}
