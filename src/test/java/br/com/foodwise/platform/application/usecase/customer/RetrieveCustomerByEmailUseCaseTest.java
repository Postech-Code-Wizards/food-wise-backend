package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.domain.entities.CustomerProfile;
import br.com.foodwise.platform.domain.repository.CustomerProfileRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.CustomerProfileResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class RetrieveCustomerByEmailUseCaseTest {

    @Mock
    private CustomerProfileRepository customerProfileRepository;

    @Mock
    private ConvertToCustomerProfileResponseUseCase convertToCustomerProfileResponseUseCase;

    @InjectMocks
    private RetrieveCustomerByEmailUseCase retrieveCustomerByEmailUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenCustomerNotFoundByEmail() {
        var email = "nonexistent@code-wizards.com";
        when(customerProfileRepository.findByUserEmail(email))
                .thenReturn(java.util.Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                retrieveCustomerByEmailUseCase.execute(email));
    }

    @Test
    void shouldRetrieveCustomerByEmailSuccessfully() {
        var email = "test@code-wizards.com";
        var customerProfile = new CustomerProfile();
        when(customerProfileRepository.findByUserEmail(email))
                .thenReturn(java.util.Optional.of(customerProfile));
        when(convertToCustomerProfileResponseUseCase.execute(customerProfile))
                .thenReturn(new CustomerProfileResponse());

        var response = retrieveCustomerByEmailUseCase.execute(email);

        assertNotNull(response);
    }

}