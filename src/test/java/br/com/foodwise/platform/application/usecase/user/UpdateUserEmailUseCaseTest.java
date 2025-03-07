package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.gateway.UserGateway;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateUserEmailUseCaseTest {

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private UpdateUserEmailUseCase updateUserEmailUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success case for User Email Update")
    void shouldUpdateUserEmailSuccessfully() {

        User userExisting = Instancio.create(User.class);
        User user = Instancio.create(User.class);

        when(userGateway.findByIdAndUserTypeAndDeletedAtIsNull(user.getId(), UserType.CUSTOMER)).thenReturn(Optional.of(userExisting));

        updateUserEmailUseCase.execute(user, user.getId(), UserType.CUSTOMER);

        verify(userGateway, times(1)).findByIdAndUserTypeAndDeletedAtIsNull(user.getId(), UserType.CUSTOMER);
        verify(userGateway, times(1)).save(any());
    }

    @Test
    @DisplayName("Fail case for User Email Update")
    void shouldThrowExceptionForNotFindingUserEmail() {

        User user = Instancio.create(User.class);
        long nonExistentUserId = 500000000L;

        ResourceNotFoundException exception = Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> updateUserEmailUseCase.execute(user, nonExistentUserId, UserType.CUSTOMER)
        );

        assertEquals("USER_DOES_NOT_EXIST", exception.getCode());
        verify(userGateway, times(1)).findByIdAndUserTypeAndDeletedAtIsNull(nonExistentUserId, UserType.CUSTOMER);
        verify(userGateway, never()).save(any());
    }

}