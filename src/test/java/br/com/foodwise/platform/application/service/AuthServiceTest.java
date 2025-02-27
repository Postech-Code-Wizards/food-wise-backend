package br.com.foodwise.platform.application.service;

import br.com.foodwise.platform.gateway.entities.UserEntity;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.BusinessException;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthServiceTest {

    @Test
    void shouldThrowException_whenUserIsDeleted() {
        var user = Instancio.create(UserEntity.class);
        user.setDeletedAt(ZonedDateTime.now());
        var authentication = mock(Authentication.class);

        when(authentication.getPrincipal()).thenReturn(user);

        assertThrows(BusinessException.class, () -> AuthService.validateUserIsActive(authentication));
    }

}