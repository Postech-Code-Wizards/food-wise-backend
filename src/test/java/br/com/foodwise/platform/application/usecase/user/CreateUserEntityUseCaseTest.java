package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.gateway.database.jpa.repository.UserRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateUserEntityUseCaseTest {

//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private CreateUserUseCase createUserUseCase;
//
//
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void createUser_shouldCreateNewUserWhenEmailDoesNotExist() {
//        var email = "newuser@code-wizards.com";
//        var password = "securePassword";
//        var role = UserType.CUSTOMER;
//
//        when(userRepository.existsByEmail(email)).thenReturn(false);
//
//        UserEntity newUserEntity = createUserUseCase.execute(email, password, role);
//
//        assertNotNull(newUserEntity);
//        assertEquals(email, newUserEntity.getEmail());
//        assertTrue(newUserEntity.isActive());
//        assertEquals(role, newUserEntity.getUserType());
//        verify(userRepository, times(1)).save(any(UserEntity.class));
//    }
//
//    @Test
//    void createUser_shouldThrowExceptionWhenEmailAlreadyExists() {
//        var email = "existing@code-wizards.com";
//        var password = "securePassword";
//        var role = UserType.CUSTOMER;
//
//        when(userRepository.existsByEmail(email)).thenReturn(true);
//
//        var exception = assertThrows(BusinessException.class, () -> createUserUseCase.execute(email, password, role));
//        assertEquals("EMAIL_ALREADY_EXISTS", exception.getCode());
//        verify(userRepository, never()).save(any(UserEntity.class));
//    }

}