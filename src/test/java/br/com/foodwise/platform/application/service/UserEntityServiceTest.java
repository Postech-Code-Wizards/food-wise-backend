package br.com.foodwise.platform.application.service;

import br.com.foodwise.platform.application.usecase.user.*;
import br.com.foodwise.platform.gateway.entities.UserEntity;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.PasswordRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserEntityServiceTest {

    @Mock
    private CreateUserUseCase createUserUseCase;

    @Mock
    private DeleteUserUseCase deleteUserUseCase;

    @Mock
    private UpdateUserEmailUseCase updateUserEmailUseCase;

    @Mock
    private UpdatePasswordUseCase updatePasswordUseCase;

    @Mock
    private LoadUserByUsernameUseCase loadUserByUsernameUseCase;

    @InjectMocks
    private UserService userService;

    private UserEntity userEntity;
    private UserRequest userRequest;
    private PasswordRequest passwordRequest;

    @BeforeEach
    void setUp() {
        userEntity = Instancio.create(UserEntity.class);
        userRequest = Instancio.create(UserRequest.class);
        passwordRequest = Instancio.create(PasswordRequest.class);
    }

    @Test
    void createUser_ShouldCallCreateUserUseCase() {
        when(createUserUseCase.execute("test@example.com", "password", UserType.CUSTOMER)).thenReturn(userEntity);

        UserEntity createdUserEntity = userService.createUser("test@example.com", "password", UserType.CUSTOMER);

        assertEquals(userEntity, createdUserEntity);
        verify(createUserUseCase, times(1)).execute("test@example.com", "password", UserType.CUSTOMER);
    }

    @Test
    void updateUserEmail_ShouldCallUpdateUserEmailUseCase() {
        userService.updateUserEmail(userRequest, 1L, UserType.CUSTOMER);

        verify(updateUserEmailUseCase, times(1)).execute(userRequest, 1L, UserType.CUSTOMER);
    }

    @Test
    void delete_ShouldCallDeleteUserUseCase() {
        userService.delete(1L, UserType.CUSTOMER);

        verify(deleteUserUseCase, times(1)).execute(1L, UserType.CUSTOMER);
    }

    @Test
    void updatePassword_ShouldCallUpdatePasswordUseCase() {
        userService.updatePassword(passwordRequest);

        verify(updatePasswordUseCase, times(1)).execute(passwordRequest);
    }

    @Test
    void loadUserByUsername_ShouldCallLoadUserByUsernameUseCase() {
        UserDetails userDetails = mock(UserDetails.class);
        when(loadUserByUsernameUseCase.loadUserByUsername("test@example.com")).thenReturn(userDetails);

        UserDetails result = userService.loadUserByUsername("test@example.com");

        assertEquals(userDetails, result);
        verify(loadUserByUsernameUseCase, times(1)).loadUserByUsername("test@example.com");
    }
}
