package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.gateway.UserGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class DeleteUserUseCaseTest {

    @Mock
    private ValidateUserIsAuthenticatedUseCase validateUserIsAuthenticatedUseCase;

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private DeleteUserUseCase deleteUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Given an existing customer user, you must delete it successfully")
    void deleteUserCustomerFound() {
        var id = 0L;
        var user = Instancio.create(User.class);


        when(userGateway.findByIdAndUserTypeAndDeletedAtIsNull(id, UserType.CUSTOMER)).thenReturn(Optional.of(user));

        deleteUserUseCase.execute(id, UserType.CUSTOMER);

        verify(userGateway, times(1)).findByIdAndUserTypeAndDeletedAtIsNull(id, UserType.CUSTOMER);
        assertEquals(Boolean.FALSE, user.isActive());
        assertNotNull(user.getDeletedAt());
    }

}