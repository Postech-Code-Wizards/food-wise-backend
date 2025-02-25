package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.domain.entities.User;
import br.com.foodwise.platform.domain.entities.enums.UserType;
import br.com.foodwise.platform.domain.repository.UserRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateUserUseCase createUserUseCase;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_shouldCreateNewUserWhenEmailDoesNotExist() {
        var email = "newuser@code-wizards.com";
        var password = "securePassword";
        var role = UserType.CUSTOMER;

        when(userRepository.existsByEmail(email)).thenReturn(false);

        User newUser = createUserUseCase.execute(email, password, role);

        assertNotNull(newUser);
        assertEquals(email, newUser.getEmail());
        assertTrue(newUser.isActive());
        assertEquals(role, newUser.getUserType());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void createUser_shouldThrowExceptionWhenEmailAlreadyExists() {
        var email = "existing@code-wizards.com";
        var password = "securePassword";
        var role = UserType.CUSTOMER;

        when(userRepository.existsByEmail(email)).thenReturn(true);

        var exception = assertThrows(BusinessException.class, () -> createUserUseCase.execute(email, password, role));
        assertEquals("EMAIL_ALREADY_EXISTS", exception.getCode());
        verify(userRepository, never()).save(any(User.class));
    }

}