package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.application.usecase.user.UpdateUserEmailUseCase;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.domain.enums.UserType;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    @DisplayName("Given an existing user, must update email successfully")
    void updateUserEmailIsSuccess(){

        User user = Instancio.create(User.class);
        Long userId = Instancio.create(Long.class);

        updateCustomerUserEmailUseCase.execute(user, userId);

        verify(updateUserEmailUseCase, times(1)).execute(user, userId, UserType.CUSTOMER);
    }

}