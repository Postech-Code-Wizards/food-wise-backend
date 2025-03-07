package br.com.foodwise.platform.application.service;

import br.com.foodwise.platform.application.usecase.user.DeleteUserUseCase;
import br.com.foodwise.platform.application.usecase.user.LoadUserByUsernameUseCase;
import br.com.foodwise.platform.application.usecase.user.UpdatePasswordUseCase;
import br.com.foodwise.platform.application.usecase.user.UpdateUserEmailUseCase;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.domain.enums.UserType;
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
class UserServiceTest {

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

    private User user;

    @BeforeEach
    void setUp() {
        user = Instancio.create(User.class);
    }

    @Test
    void updateUserEmail_ShouldCallUpdateUserEmailUseCase() {
        userService.updateUserEmail(user, 1L, UserType.CUSTOMER);

        verify(updateUserEmailUseCase, times(1)).execute(user, 1L, UserType.CUSTOMER);
    }

    @Test
    void delete_ShouldCallDeleteUserUseCase() {
        userService.delete(1L, UserType.CUSTOMER);

        verify(deleteUserUseCase, times(1)).execute(1L, UserType.CUSTOMER);
    }

    @Test
    void updatePassword_ShouldCallUpdatePasswordUseCase() {
        String newPassword = Instancio.create(String.class);
        userService.updatePassword(user, newPassword);

        verify(updatePasswordUseCase, times(1)).execute(user, newPassword);
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
