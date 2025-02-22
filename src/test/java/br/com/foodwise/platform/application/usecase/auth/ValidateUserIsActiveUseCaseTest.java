package br.com.foodwise.platform.application.usecase.auth;

import br.com.foodwise.platform.application.service.AuthService;
import br.com.foodwise.platform.domain.entities.User;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.BusinessException;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ValidateUserIsActiveUseCaseTest {


    @Test
    void shouldCreateValidateUserIsActiveUseCase() {
        ValidateUserIsActiveUseCase validateUserIsActiveUseCase = new ValidateUserIsActiveUseCase();

        assertNotNull(validateUserIsActiveUseCase, "The class instance must not be null.");
    }

    @Test
    void shouldThrowException_whenUserIsDeleted() {
        var user = Instancio.create(User.class);
        user.setDeletedAt(ZonedDateTime.now());
        var authentication = mock(Authentication.class);

        when(authentication.getPrincipal()).thenReturn(user);

        assertThrows(BusinessException.class, () -> AuthService.validateUserIsActive(authentication));
    }

    @Test
    void shouldThrowExceptionWhenUserIsActive() {
        var user = Instancio.create(User.class);
        user.setDeletedAt(null);
        var authentication = mock(Authentication.class);

        when(authentication.getPrincipal()).thenReturn(user);

        assertDoesNotThrow(() -> AuthService.validateUserIsActive(authentication));
    }

}