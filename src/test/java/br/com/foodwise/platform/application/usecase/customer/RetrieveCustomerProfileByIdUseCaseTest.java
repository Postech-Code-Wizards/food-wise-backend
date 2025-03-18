package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.domain.CustomerProfile;
import br.com.foodwise.platform.gateway.CustomerProfileGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveCustomerProfileByIdUseCaseTest {

    @Mock
    private CustomerProfileGateway customerProfileGateway;

    @InjectMocks
    private RetrieveCustomerProfileByIdUseCase useCase;

    @Test
    @DisplayName("Should retrieve customer profile by id successfully")
    void retrieveById_ShouldRetrieveCustomerProfile() {

        Long customerProfileId = Instancio.create(Long.class);
        CustomerProfile customerProfile = Instancio.create(CustomerProfile.class);

        when(customerProfileGateway.findById(customerProfileId)).thenReturn(customerProfile);

        CustomerProfile result = useCase.retrieveById(customerProfileId);

        assertNotNull(result);
        assertEquals(customerProfile, result);
    }

}