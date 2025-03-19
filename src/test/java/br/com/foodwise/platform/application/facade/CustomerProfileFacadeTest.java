package br.com.foodwise.platform.application.facade;

import br.com.foodwise.platform.application.facade.converter.common.UserRequestToDomainConverter;
import br.com.foodwise.platform.application.facade.converter.customer.CustomerProfileDomainToResponseConverter;
import br.com.foodwise.platform.application.facade.converter.customer.CustomerProfileRequestToDomainConverter;
import br.com.foodwise.platform.application.usecase.customer.DeleteCustomerProfileUseCase;
import br.com.foodwise.platform.application.usecase.customer.RegisterCustomerUseCase;
import br.com.foodwise.platform.application.usecase.customer.RetrieveCustomerByEmailAuthenticatedUseCase;
import br.com.foodwise.platform.application.usecase.customer.RetrieveCustomerByEmailUseCase;
import br.com.foodwise.platform.application.usecase.customer.UpdateCustomerProfileUseCase;
import br.com.foodwise.platform.application.usecase.customer.UpdateCustomerUserEmailUseCase;
import br.com.foodwise.platform.application.usecase.user.DeleteUserUseCase;
import br.com.foodwise.platform.domain.CustomerProfile;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.CustomerProfileRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.RegisterCustomerRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.CustomerProfileResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerProfileFacadeTest {

    @Mock
    private DeleteUserUseCase deleteUserUseCase;

    @Mock
    private UpdateCustomerUserEmailUseCase updateCustomerUserEmailUseCase;

    @Mock
    private DeleteCustomerProfileUseCase deleteCustomerProfileUseCase;

    @Mock
    private RetrieveCustomerByEmailAuthenticatedUseCase retrieveCustomerByEmailAuthenticatedUseCase;

    @Mock
    private RegisterCustomerUseCase registerCustomerUseCase;

    @Mock
    private UpdateCustomerProfileUseCase updateCustomerProfileUseCase;

    @Mock
    private CustomerProfileRequestToDomainConverter customerProfileRequestToDomainConverter;

    @Mock
    private CustomerProfileDomainToResponseConverter customerProfileDomainToResponseConverter;

    @Mock
    private UserRequestToDomainConverter userRequestToDomainConverter;

    @Mock
    private RetrieveCustomerByEmailUseCase retrieveCustomerByEmailUseCase;

    @InjectMocks
    private CustomerProfileFacade customerProfileFacade;

    private CustomerProfile customerProfile;
    private User user;
    private RegisterCustomerRequest registerCustomerRequest;
    private CustomerProfileRequest customerProfileRequest;
    private UserRequest userRequest;
    private CustomerProfileResponse customerProfileResponse;

    @BeforeEach
    void setUp() {
        customerProfile = Instancio.create(CustomerProfile.class);
        user = Instancio.create(User.class);
        registerCustomerRequest = Instancio.create(RegisterCustomerRequest.class);
        customerProfileRequest = Instancio.create(CustomerProfileRequest.class);
        userRequest = Instancio.create(UserRequest.class);
        customerProfileResponse = Instancio.create(CustomerProfileResponse.class);
    }

    @Test
    @DisplayName("Must call service to register a customer successfully")
    void registerCustomer_ShouldCallRegisterCustomerUseCase() {
        when(userRequestToDomainConverter.convert(registerCustomerRequest.getUser())).thenReturn(user);
        when(customerProfileRequestToDomainConverter.convert(any(CustomerProfileRequest.class), any(User.class))).thenReturn(customerProfile);

        customerProfileFacade.registerCustomer(registerCustomerRequest);

        verify(registerCustomerUseCase, times(1)).execute(any(CustomerProfile.class));
    }

    @Test
    @DisplayName("Must call service to update a customer successfully")
    void updateCustomerProfile_ShouldCallUpdateCustomerProfileUseCase() {
        when(customerProfileRequestToDomainConverter.convert(customerProfileRequest)).thenReturn(customerProfile);

        customerProfileFacade.updateCustomerProfile(customerProfileRequest, 1L);

        verify(updateCustomerProfileUseCase, times(1)).execute(customerProfile, 1L);
    }

    @Test
    @DisplayName("Must call service to update email a customer successfully")
    void updateCustomerUserEmail_ShouldCallUpdateCustomerUserEmailUseCase() {
        when(userRequestToDomainConverter.convert(userRequest)).thenReturn(user);

        customerProfileFacade.updateCustomerUserEmail(userRequest, 1L);

        verify(updateCustomerUserEmailUseCase, times(1)).execute(user, 1L);
    }

    @Test
    @DisplayName("Must call service to retrieve by email authenticated customer successfully")
    void retrieveCustomerByEmail_ShouldCallRetrieveCustomerByEmailAuthenticatedUseCase() {
        when(retrieveCustomerByEmailAuthenticatedUseCase.execute()).thenReturn(customerProfile);
        when(customerProfileDomainToResponseConverter.convert(customerProfile)).thenReturn(customerProfileResponse);

        var result = customerProfileFacade.retrieveCustomerByEmailAuthenticated();

        assertEquals(customerProfileResponse, result);
        verify(retrieveCustomerByEmailAuthenticatedUseCase, times(1)).execute();
    }

    @Test
    @DisplayName("Must call service to retrieve by email a customer successfully")
    void retrieveCustomerByEmail_ShouldCallRetrieveCustomerByEmailUseCase() {
        when(retrieveCustomerByEmailUseCase.execute(anyString())).thenReturn(customerProfile);
        when(customerProfileDomainToResponseConverter.convert(customerProfile)).thenReturn(customerProfileResponse);

        var result = customerProfileFacade.retrieveCustomerByEmail(anyString());

        assertEquals(customerProfileResponse, result);
        verify(retrieveCustomerByEmailUseCase, times(1)).execute(anyString());
    }

    @Test
    @DisplayName("Must call service to delete a customer successfully")
    void delete_ShouldCallDeleteCustomerProfileUseCase() {
        customerProfileFacade.delete(1L);

        verify(deleteCustomerProfileUseCase, times(1)).execute(1L);
    }
}
