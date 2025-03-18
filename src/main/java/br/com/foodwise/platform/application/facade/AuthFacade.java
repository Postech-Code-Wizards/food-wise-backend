package br.com.foodwise.platform.application.facade;

import br.com.foodwise.platform.application.usecase.auth.ValidateUserIsActiveUseCase;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthFacade {

    public AuthFacade() {
    }

    public static void validateUserIsActive(Authentication auth) {
        ValidateUserIsActiveUseCase.execute(auth);
    }

}
