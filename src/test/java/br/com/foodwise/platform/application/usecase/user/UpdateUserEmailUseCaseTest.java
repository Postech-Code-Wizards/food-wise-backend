package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.domain.entities.User;
import br.com.foodwise.platform.domain.entities.enums.UserType;
import br.com.foodwise.platform.domain.repository.UserRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;
import java.util.Optional;

import static br.com.foodwise.platform.factory.RequestFactory.buildUserEntity;
import static br.com.foodwise.platform.factory.RequestFactory.buildUserRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateUserEmailUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ConvertUserRequestToUserUseCase convertUserRequestToUserUseCase;

    @InjectMocks
    private UpdateUserEmailUseCase updateUserEmailUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success case for User Email Update")
    void shouldUpdateUserEmailSuccessfully() {

        long id = 1L;
        UserRequest userNewData = Instancio.create(UserRequest.class);

        var user = buildUserEntity();
        when(userRepository.findByIdAndUserTypeAndDeletedAtIsNull(id, UserType.CUSTOMER)).thenReturn(Optional.of(user));

        var userEntity = Instancio.create(User.class);
        userEntity.setUpdatedAt(ZonedDateTime.now());
        when(convertUserRequestToUserUseCase.execute(any())).thenReturn(userEntity);

        updateUserEmailUseCase.execute(userNewData, id, UserType.CUSTOMER);

        verify(userRepository, times(1)).findByIdAndUserTypeAndDeletedAtIsNull(id, UserType.CUSTOMER);
        verify(userRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Fail case for User Email Update")
    void shouldThrowExceptionForNotFindingUserEmail() {
        UserRequest userNewData = buildUserRequest();

        long nonExistentUserId = 500000000L;

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> updateUserEmailUseCase.execute(userNewData, nonExistentUserId, UserType.CUSTOMER)
        );

        assertEquals("USER_DOES_NOT_EXIST", exception.getCode());

        verify(userRepository, times(1)).findByIdAndUserTypeAndDeletedAtIsNull(nonExistentUserId, UserType.CUSTOMER);

        verify(userRepository, never()).save(any());
    }

}