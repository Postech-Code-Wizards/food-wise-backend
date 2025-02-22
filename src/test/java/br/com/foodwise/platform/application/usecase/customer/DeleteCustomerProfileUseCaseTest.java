package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.application.usecase.user.DeleteUserUseCase;
import br.com.foodwise.platform.domain.entities.enums.UserType;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeleteCustomerProfileUseCaseTest {

    @Mock
    private DeleteUserUseCase deleteUserUseCase;

    @InjectMocks
    private DeleteCustomerProfileUseCase deleteCustomerProfileUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteCustomerProfileIsSuccessful() {

        var id = Instancio.create(long.class);
        deleteCustomerProfileUseCase.execute(id);
        verify(deleteUserUseCase, times(1)).execute(id, UserType.CUSTOMER);

    }

}