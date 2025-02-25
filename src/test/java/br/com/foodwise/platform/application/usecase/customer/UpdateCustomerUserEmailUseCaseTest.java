package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.application.usecase.user.ConvertUserRequestToUserUseCase;
import br.com.foodwise.platform.application.usecase.user.UpdateUserEmailUseCase;
import br.com.foodwise.platform.domain.entities.User;
import br.com.foodwise.platform.domain.entities.enums.UserType;
import br.com.foodwise.platform.domain.repository.UserRepository;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UpdateCustomerUserEmailUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ConvertUserRequestToUserUseCase convertUserRequestToUserUseCase;

    @Mock
    private UpdateUserEmailUseCase updateUserEmailUseCase;

    @InjectMocks
    private UpdateCustomerUserEmailUseCase updateCustomerUserEmailUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateUserEmailIsSuccess(){

        UserRequest userRequest = Instancio.create(UserRequest.class);
        Long userId = Instancio.create(Long.class);
        User user = Instancio.create(User.class);

        doNothing().when(updateUserEmailUseCase).execute(userRequest, userId, UserType.CUSTOMER);
        when(userRepository.findByIdAndUserTypeAndDeletedAtIsNull(userId, UserType.CUSTOMER)).thenReturn(Optional.ofNullable(user));
        when(convertUserRequestToUserUseCase.execute(userRequest)).thenReturn(user);

        updateCustomerUserEmailUseCase.execute(userRequest, userId);

        verify(updateUserEmailUseCase, times(1)).execute(userRequest, userId, UserType.CUSTOMER);
    }

    @Test
    void testExecuteThrowsException() {

        UserRequest userRequest = Instancio.create(UserRequest.class);
        Long userId = Instancio.create(Long.class);

        when(userRepository.findByIdAndUserTypeAndDeletedAtIsNull(userId, UserType.CUSTOMER)).thenReturn(Optional.empty());

        doThrow(new RuntimeException("Erro ao atualizar e-mail")).when(updateUserEmailUseCase)
                .execute(userRequest, userId, UserType.CUSTOMER);

        assertThrows(RuntimeException.class, () -> updateCustomerUserEmailUseCase.execute(userRequest, userId));

    }

}