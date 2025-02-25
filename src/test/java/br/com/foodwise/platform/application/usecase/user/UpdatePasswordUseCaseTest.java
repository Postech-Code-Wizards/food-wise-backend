package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.domain.entities.User;
import br.com.foodwise.platform.domain.repository.UserRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.BusinessException;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.PasswordRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UpdatePasswordUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private UpdatePasswordUseCase updatePasswordUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void testExecuteShouldThrowBusinessExceptionWhenPasswordIsIncorrect() {

        PasswordRequest passwordRequest = Instancio.create(PasswordRequest.class);
        User user = Instancio.create(User.class);

        when(authentication.getPrincipal()).thenReturn(user);
        BCryptPasswordEncoder encoder = mock(BCryptPasswordEncoder.class);
        when(encoder.matches(passwordRequest.getPassword(), user.getPassword())).thenReturn(false);

        assertThrows(BusinessException.class, () -> updatePasswordUseCase.execute(passwordRequest));

    }

    @Test
    void testExecuteShouldUpdatePasswordAndUpdateDateWhenPasswordIsCorrect() {

        PasswordRequest passwordRequest = Instancio.create(PasswordRequest.class);
        User user = Instancio.create(User.class);
        user.setPassword(new BCryptPasswordEncoder().encode(passwordRequest.getPassword()));

        when(authentication.getPrincipal()).thenReturn(user);
        updatePasswordUseCase.execute(passwordRequest);

        verify(userRepository).save(user);
    }

}