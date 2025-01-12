package br.com.foodwise.platform.service;

import br.com.foodwise.platform.model.entities.CustomerProfile;
import br.com.foodwise.platform.model.entities.User;
import br.com.foodwise.platform.model.entities.enums.UserType;
import br.com.foodwise.platform.model.repositories.CustomerProfileRepository;
import br.com.foodwise.platform.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.platform.rest.converter.customer.CustomerProfileEntityToResponseConverter;
import br.com.foodwise.platform.rest.converter.customer.CustomerProfileRequestToEntityConverter;
import br.com.foodwise.platform.rest.dtos.request.register.customer.CustomerProfileRequest;
import br.com.foodwise.platform.rest.dtos.response.CustomerProfileResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.foodwise.platform.factory.RequestFactory.buildCustomerProfileRequest;
import static br.com.foodwise.platform.factory.RequestFactory.buildValidRegisterCustomerRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class CustomerProfileServiceTest {


    @Mock
    private CustomerProfileRepository customerProfileRepository;

    @Mock
    private CustomerProfileRequestToEntityConverter customerProfileRequestToEntityConverter;

    @Mock
    private CustomerProfileEntityToResponseConverter customerProfileEntityToResponseConverter;

    @Mock
    private UserService userService;

    @InjectMocks
    private CustomerProfileService customerProfileService;

    private CustomerProfileRequest customerProfileRequest;

    @BeforeEach
    void setUp() {
        customerProfileRequest = buildCustomerProfileRequest();
    }

    @Test
    void shouldRegisterCustomerSuccessfully() {
        var registerCustomerRequest = buildValidRegisterCustomerRequest();
        var userRequest = registerCustomerRequest.getUser();
        var user = new User();
        when(userService.createUser(userRequest.getEmail(), userRequest.getPassword(), UserType.CUSTOMER))
                .thenReturn(user);

        var customerEntity = new CustomerProfile();
        when(customerProfileRequestToEntityConverter.convert(any()))
                .thenReturn(customerEntity);

        customerProfileService.registerCustomer(registerCustomerRequest);

        verify(customerProfileRepository).save(customerEntity);
    }

    @Test
    void shouldRetrieveCustomerByEmailSuccessfully() {
        var email = "test@code-wizards.com";
        var customerProfile = new CustomerProfile();
        when(customerProfileRepository.findByUserEmail(email))
                .thenReturn(java.util.Optional.of(customerProfile));
        when(customerProfileEntityToResponseConverter.convert(customerProfile))
                .thenReturn(new CustomerProfileResponse());

        var response = customerProfileService.retrieveCustomerByEmail(email);

        assertNotNull(response);
    }

    @Test
    void shouldThrowExceptionWhenCustomerConversionFails() {
        when(customerProfileRequestToEntityConverter.convert(any()))
                .thenReturn(null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> customerProfileService
                        .convertToCustomerProfileEntity(customerProfileRequest));

        assertEquals("Customer profile conversion failed.",
                exception.getMessage());
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenCustomerNotFoundByEmail() {
        var email = "nonexistent@code-wizards.com";
        when(customerProfileRepository.findByUserEmail(email))
                .thenReturn(java.util.Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                customerProfileService.retrieveCustomerByEmail(email));
    }

    @Test
    void shouldThrowExceptionWhenCustomerProfileRequestIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
                customerProfileService.convertToCustomerProfileEntity(null));
    }

    @Test
    void deleteCustomer(){
        var id = Instancio.create(long.class);
        customerProfileService.delete(id);
        verify(userService, times(1)).delete(id, UserType.CUSTOMER);
    }

}
