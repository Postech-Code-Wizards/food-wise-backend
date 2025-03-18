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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class UpdateCustomerProfileUseCaseTest {

    @Mock
    private CustomerProfileGateway customerProfileGateway;

    @InjectMocks
    private UpdateCustomerProfileUseCase updateCustomerProfileUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success case for Customer Profile Update")
    void shouldUpdateCustomerProfileSuccessfully() {
        CustomerProfile customerProfile = Instancio.create(CustomerProfile.class);

        when(customerProfileGateway.findById(anyLong())).thenReturn(customerProfile);

        updateCustomerProfileUseCase.execute(customerProfile, anyLong());

        verify(customerProfileGateway, times(1)).findById(anyLong());
    }

}