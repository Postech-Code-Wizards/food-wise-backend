package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.domain.CustomerProfile;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.gateway.CustomerProfileGateway;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static br.com.foodwise.platform.factory.SecurityHelperFactory.authenticateUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class RetrieveCustomerByEmailAuthenticatedUseCaseTest {

    @Mock
    private CustomerProfileGateway customerProfileGateway;

    @InjectMocks
    private RetrieveCustomerByEmailAuthenticatedUseCase retrieveCustomerByEmailAuthenticatedUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Given an existing email, you must return the customer")
    void shouldRetrieveCustomerByEmailSuccessfully() {
        var customerProfile = Instancio.create(CustomerProfile.class);
        var user = Instancio.create(UserEntity.class);

        authenticateUser(user.getEmail(), user.getPassword(), UserType.CUSTOMER);

        when(customerProfileGateway.findByUserEmail(user.getEmail()))
                .thenReturn(customerProfile);

        var response = retrieveCustomerByEmailAuthenticatedUseCase.execute();

        assertNotNull(response);
        assertEquals(response, customerProfile);
    }

}