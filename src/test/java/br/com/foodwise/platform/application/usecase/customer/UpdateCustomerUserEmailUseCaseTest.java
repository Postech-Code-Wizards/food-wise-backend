package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.application.usecase.user.UpdateUserEmailUseCase;
import br.com.foodwise.platform.domain.entities.enums.UserType;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UpdateCustomerUserEmailUseCaseTest {

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
        UserType userType = Instancio.create(UserType.class);

        doNothing().when(updateUserEmailUseCase).execute(userRequest, userId, userType);

        updateCustomerUserEmailUseCase.execute(userRequest, userId);

        verify(updateUserEmailUseCase, times(1)).execute(userRequest, userId, UserType.CUSTOMER);
    }

    @Test
    void testExecuteThrowsException() {

        UserRequest userRequest = Instancio.create(UserRequest.class);
        Long userId = Instancio.create(Long.class);

        doThrow(new RuntimeException("Erro ao atualizar email")).when(updateUserEmailUseCase)
                .execute(userRequest, userId, UserType.CUSTOMER);

        assertThrows(RuntimeException.class, () -> {
            updateCustomerUserEmailUseCase.execute(userRequest, userId);
        });
    }


}