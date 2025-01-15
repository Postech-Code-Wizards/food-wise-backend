package br.com.foodwise.platform.service;

import br.com.foodwise.platform.model.entities.RestaurantProfile;
import br.com.foodwise.platform.model.entities.User;
import br.com.foodwise.platform.model.entities.enums.UserType;
import br.com.foodwise.platform.model.repositories.UserRepository;
import br.com.foodwise.platform.rest.controller.exception.BusinessException;
import br.com.foodwise.platform.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.platform.rest.converter.common.UserRequestToEntityConverter;
import br.com.foodwise.platform.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.rest.dtos.request.register.customer.PasswordRequest;
import br.com.foodwise.platform.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.ZonedDateTime;
import java.util.Optional;

import static br.com.foodwise.platform.factory.RequestFactory.*;
import static br.com.foodwise.platform.factory.SecurityHelperFactory.buildMockUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRequestToEntityConverter userRequestToEntityConverter;

    @InjectMocks
    private UserService userService;

    @Test
    void loadUserByUsername_shouldReturnUserDetailsWhenEmailExists() {
        var email = "test@code-wizards.com";
        var mockUser = buildMockUser(email, "password", UserType.CUSTOMER);
        when(userRepository.findByEmail(email)).thenReturn(mockUser);

        var userDetails = userService.loadUserByUsername(email);

        assertNotNull(userDetails);
    }

    @Test
    void createUser_shouldCreateNewUserWhenEmailDoesNotExist() {
        var email = "newuser@code-wizards.com";
        var password = "securePassword";
        var role = UserType.CUSTOMER;

        when(userRepository.existsByEmail(email)).thenReturn(false);

        User newUser = userService.createUser(email, password, role);

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

        var exception = assertThrows(BusinessException.class, () -> userService.createUser(email, password, role));
        assertEquals("EMAIL_ALREADY_EXISTS", exception.getCode());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void createUser_shouldEncryptPasswordBeforeSaving() {
        var email = "secureuser@code-wizards.com";
        var password = "plainPassword";
        var role = UserType.ADMIN;

        when(userRepository.existsByEmail(email)).thenReturn(false);

        var newUser = userService.createUser(email, password, role);

        assertNotNull(newUser.getPassword());
        assertNotEquals(password, newUser.getPassword());
        assertTrue(new BCryptPasswordEncoder().matches(password, newUser.getPassword()));
        verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("Success case for User Email Update")
    void shouldUpdateUserEmailSuccessfully() {

        UserRequest userNewData = buildUserRequest();

        var user = buildUserEntity();

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        var userEntity = new User();
        userEntity.setUpdatedAt(ZonedDateTime.now());
        when(userRequestToEntityConverter.convert(any())).thenReturn(userEntity);

        userService.updateUserEmail(userNewData, anyLong());

        verify(userRepository, times(1)).findById(anyLong());
        assertEquals(userNewData.getEmail(), user.getEmail());
        assertNotNull(user.getUpdatedAt());
    }

    @Test
    @DisplayName("Fail case for User Email Update")
    void shouldThrowExceptionForNotFindingUserEmail() {
        UserRequest userNewData = buildUserRequest();

        long nonExistentUserId = 500000000L;

        doReturn(Optional.empty()).when(userRepository).findById(nonExistentUserId);

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> userService.updateUserEmail(userNewData, nonExistentUserId)
        );

        assertEquals("USER_DOES_NOT_EXIST", exception.getCode());

        verify(userRepository, times(1)).findById(nonExistentUserId);

        verify(userRepository, never()).save(any());
    }

    @Test
    void shouldthrowexceptiondutoincorrectpassword(){
        PasswordRequest passwordRequestNew = buildPasswordRequest();

        var user = buildUserEntity();

        Long id = 5549875L;

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> userService.updatePassword(passwordRequestNew, id)
        );

        assertEquals("INCORRECT_PASSWORD", exception.getCode());

        verify(userRepository, times(1)).findById(id);

        verify(userRepository, never()).save(any());
    }
}
