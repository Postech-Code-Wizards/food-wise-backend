package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.domain.CustomerProfile;
import br.com.foodwise.platform.gateway.CustomerProfileGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class RetrieveCustomerByEmailUseCaseTest {

    @Mock
    private CustomerProfileGateway customerProfileGateway;

    @InjectMocks
    private RetrieveCustomerByEmailUseCase retrieveCustomerByEmailUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Given an existing email, you must return the customer")
    void shouldRetrieveCustomerByEmailSuccessfully() {
        var customerProfile = Instancio.create(CustomerProfile.class);

        when(customerProfileGateway.findByUserEmail(anyString()))
                .thenReturn(customerProfile);

        var response = retrieveCustomerByEmailUseCase.execute(anyString());

        assertNotNull(response);
        assertEquals(response, customerProfile);
    }

}