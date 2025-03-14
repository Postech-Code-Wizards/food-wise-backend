package br.com.foodwise.platform.application.usecase.auth;

import br.com.foodwise.platform.application.facade.AuthFacade;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.BusinessException;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ValidateUserIsActiveUseCaseTest {

    @Test
    void shouldThrowException_whenUserIsDeleted() {
        var user = Instancio.create(UserEntity.class);
        user.setDeletedAt(ZonedDateTime.now());
        var authentication = mock(Authentication.class);

        when(authentication.getPrincipal()).thenReturn(user);

        assertThrows(BusinessException.class, () -> AuthFacade.validateUserIsActive(authentication));
    }

    @Test
    void shouldThrowExceptionWhenUserIsActive() {
        var user = Instancio.create(UserEntity.class);
        user.setDeletedAt(null);
        var authentication = mock(Authentication.class);

        when(authentication.getPrincipal()).thenReturn(user);

        assertDoesNotThrow(() -> AuthFacade.validateUserIsActive(authentication));
    }

}