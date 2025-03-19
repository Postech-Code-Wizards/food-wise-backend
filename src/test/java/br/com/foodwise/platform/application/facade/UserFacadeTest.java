package br.com.foodwise.platform.application.facade;

import br.com.foodwise.platform.application.facade.converter.common.PasswordRequestToDomainConverter;
import br.com.foodwise.platform.application.usecase.user.DeleteUserUseCase;
import br.com.foodwise.platform.application.usecase.user.LoadUserByUsernameUseCase;
import br.com.foodwise.platform.application.usecase.user.UpdatePasswordUseCase;
import br.com.foodwise.platform.application.usecase.user.UpdateUserEmailUseCase;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.PasswordRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserFacadeTest {

    @Mock
    private DeleteUserUseCase deleteUserUseCase;

    @Mock
    private UpdateUserEmailUseCase updateUserEmailUseCase;

    @Mock
    private UpdatePasswordUseCase updatePasswordUseCase;

    @Mock
    private LoadUserByUsernameUseCase loadUserByUsernameUseCase;

    @Mock
    private PasswordRequestToDomainConverter passwordRequestToDomainConverter;

    @InjectMocks
    private UserFacade userFacade;

    private User user;
    private PasswordRequest passwordRequest;

    @BeforeEach
    void setUp() {
        user = Instancio.create(User.class);
        passwordRequest = Instancio.create(PasswordRequest.class);
    }

    @Test
    void updateUserEmail_ShouldCallUpdateUserEmailUseCase() {

        userFacade.updateUserEmail(user, 1L, UserType.CUSTOMER);

        verify(updateUserEmailUseCase, times(1)).execute(user, 1L, UserType.CUSTOMER);
    }

    @Test
    void delete_ShouldCallDeleteUserUseCase() {
        userFacade.delete(1L, UserType.CUSTOMER);

        verify(deleteUserUseCase, times(1)).execute(1L, UserType.CUSTOMER);
    }

    @Test
    void updatePassword_ShouldCallUpdatePasswordUseCase() {

        when(passwordRequestToDomainConverter.convert(passwordRequest)).thenReturn(user);

        userFacade.updatePassword(passwordRequest);

        verify(updatePasswordUseCase, times(1)).execute(any(User.class), anyString());
    }

    @Test
    void loadUserByUsername_ShouldCallLoadUserByUsernameUseCase() {
        UserDetails userDetails = mock(UserDetails.class);
        when(loadUserByUsernameUseCase.loadUserByUsername("test@example.com")).thenReturn(userDetails);

        UserDetails result = userFacade.loadUserByUsername("test@example.com");

        assertEquals(userDetails, result);
        verify(loadUserByUsernameUseCase, times(1)).loadUserByUsername("test@example.com");
    }
}
