package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.BusinessException;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class ValidateUserIsAuthenticatedUseCaseTest {

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @Mock
    private UserEntity userEntityAuthenticated;

    @InjectMocks
    private ValidateUserIsAuthenticatedUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should pass when user match")
    void execute_shouldPass_whenUserIdsMatch() {
        long userId = Instancio.create(Long.class);

        try (MockedStatic<SecurityContextHolder> utilities = mockStatic(SecurityContextHolder.class)) {
            utilities.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(userEntityAuthenticated);
            when(userEntityAuthenticated.getId()).thenReturn(userId);

            assertDoesNotThrow(() -> useCase.execute(userId));
        }
    }

    @Test
    @DisplayName("Should throw BusinessException when user do not match")
    void execute_shouldThrowBusinessException_whenUserIdsDoNotMatch() {
        long userId = 1L;
        long authenticatedUserId = 2L;

        try (MockedStatic<SecurityContextHolder> utilities = mockStatic(SecurityContextHolder.class)) {
            utilities.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(userEntityAuthenticated);
            when(userEntityAuthenticated.getId()).thenReturn(authenticatedUserId);

            BusinessException exception = assertThrows(BusinessException.class, () -> useCase.execute(userId));

            assertEquals("DELETION_OF_UNAUTHENTICATED", exception.getCode());
        }
    }
}