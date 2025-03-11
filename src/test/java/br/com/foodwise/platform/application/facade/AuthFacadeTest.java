package br.com.foodwise.platform.application.facade;

import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.BusinessException;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthFacadeTest {

    @Test
    @DisplayName("Should throw exception when user is deleted")
    void shouldThrowException_whenUserIsDeleted() {
        var user = Instancio.create(UserEntity.class);
        user.setDeletedAt(ZonedDateTime.now());
        var authentication = mock(Authentication.class);

        when(authentication.getPrincipal()).thenReturn(user);

        assertThrows(BusinessException.class, () -> AuthFacade.validateUserIsActive(authentication));
    }

    @Test
    @DisplayName("Must test the class constructor")
    void testAuthFacadeConstructor() {
        AuthFacade authFacade = new AuthFacade();
        assertNotNull(authFacade);
    }

}