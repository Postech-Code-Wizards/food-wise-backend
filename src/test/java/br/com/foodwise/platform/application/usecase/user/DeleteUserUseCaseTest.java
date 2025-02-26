package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.domain.entities.User;
import br.com.foodwise.platform.domain.entities.enums.UserType;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class DeleteUserUseCaseTest {

    @Mock
    private ValidateUserIsAuthenticatedUseCase validateUserIsAuthenticatedUseCase;

    @Mock
    private FindActiveUserUseCase findActiveUserUseCase;

    @InjectMocks
    private DeleteUserUseCase deleteUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteUserCustomerFound() {
        final var id = 0L;
        final var user = Instancio.create(User.class);

        when(findActiveUserUseCase.execute(id, UserType.CUSTOMER)).thenReturn(user);

        deleteUserUseCase.execute(id, UserType.CUSTOMER);

        verify(findActiveUserUseCase, times(1)).execute(id, UserType.CUSTOMER);
        assertEquals(Boolean.FALSE, user.isActive());
        assertNotNull(user.getDeletedAt());
    }

}