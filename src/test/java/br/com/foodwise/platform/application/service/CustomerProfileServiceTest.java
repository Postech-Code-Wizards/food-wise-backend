package br.com.foodwise.platform.application.service;

import br.com.foodwise.platform.application.usecase.customer.*;
import br.com.foodwise.platform.application.usecase.user.DeleteUserUseCase;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.CustomerProfileRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.RegisterCustomerRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.CustomerProfileResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerProfileServiceTest {

    @Mock
    private DeleteUserUseCase deleteUserUseCase;

    @Mock
    private UpdateCustomerUserEmailUseCase updateCustomerUserEmailUseCase;

    @Mock
    private DeleteCustomerProfileUseCase deleteCustomerProfileUseCase;

    @Mock
    private RetrieveCustomerByEmailUseCase retrieveCustomerByEmailUseCase;

    @Mock
    private RegisterCustomerUseCase registerCustomerUseCase;

    @Mock
    private UpdateCustomerProfileUseCase updateCustomerProfileUseCase;

    @InjectMocks
    private CustomerProfileService customerProfileService;

    private RegisterCustomerRequest registerCustomerRequest;
    private CustomerProfileRequest customerProfileRequest;
    private UserRequest userRequest;
    private CustomerProfileResponse customerProfileResponse;

    @BeforeEach
    void setUp() {
        registerCustomerRequest = Instancio.create(RegisterCustomerRequest.class);
        customerProfileRequest = Instancio.create(CustomerProfileRequest.class);
        userRequest = Instancio.create(UserRequest.class);
        customerProfileResponse = Instancio.create(CustomerProfileResponse.class);
    }

    @Test
    void registerCustomer_ShouldCallRegisterCustomerUseCase() {
        customerProfileService.registerCustomer(registerCustomerRequest);

        verify(registerCustomerUseCase, times(1)).execute(registerCustomerRequest);
    }

    @Test
    void updateCustomerProfile_ShouldCallUpdateCustomerProfileUseCase() {
        customerProfileService.updateCustomerProfile(customerProfileRequest, 1L);

        verify(updateCustomerProfileUseCase, times(1)).execute(customerProfileRequest, 1L);
    }

    @Test
    void updateCustomerUserEmail_ShouldCallUpdateCustomerUserEmailUseCase() {
        customerProfileService.updateCustomerUserEmail(userRequest, 1L);

        verify(updateCustomerUserEmailUseCase, times(1)).execute(userRequest, 1L);
    }

    @Test
    void retrieveCustomerByEmail_ShouldCallRetrieveCustomerByEmailUseCase() {
        String email = "test@example.com";
        when(retrieveCustomerByEmailUseCase.execute(email)).thenReturn(customerProfileResponse);

        CustomerProfileResponse result = customerProfileService.retrieveCustomerByEmail(email);

        assertEquals(customerProfileResponse, result);
        verify(retrieveCustomerByEmailUseCase, times(1)).execute(email);
    }

    @Test
    void delete_ShouldCallDeleteCustomerProfileUseCase() {
        customerProfileService.delete(1L);

        verify(deleteCustomerProfileUseCase, times(1)).execute(1L);
    }
}
