package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.domain.CustomerProfile;
import br.com.foodwise.platform.gateway.CustomerProfileGateway;
import br.com.foodwise.platform.gateway.UserGateway;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.BusinessException;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RegisterCustomerUseCaseTest {

    @Mock
    private CustomerProfileGateway customerProfileGateway;

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private RegisterCustomerUseCase registerCustomerUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldRegisterCustomerSuccessfully() {

        var customer = Instancio.create(CustomerProfile.class);

        registerCustomerUseCase.execute(customer);

        verify(customerProfileGateway).save(customer);
    }

    @Test
    void shouldRegisterCustomerException() {

        var customer = Instancio.create(CustomerProfile.class);

        when(userGateway.existsByEmail(customer.getUser().getEmail()))
                .thenReturn(true);

        assertThrows(BusinessException.class, () ->
                registerCustomerUseCase.execute(customer));
    }

}