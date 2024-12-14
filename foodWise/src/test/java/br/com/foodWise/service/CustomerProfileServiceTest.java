package br.com.foodwise.service;

import br.com.foodwise.model.entities.CustomerProfile;
import br.com.foodwise.model.entities.User;
import br.com.foodwise.model.entities.enums.UserType;
import br.com.foodwise.model.repositories.CustomerProfileRepository;
import br.com.foodwise.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.rest.converter.customer.CustomerProfileEntityToResponseConverter;
import br.com.foodwise.rest.converter.customer.CustomerProfileRequestToEntityConverter;
import br.com.foodwise.rest.dtos.request.register.customer.CustomerProfileRequest;
import br.com.foodwise.rest.dtos.response.CustomerProfileResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.foodwise.factory.RequestFactory.buildValidCustomerRequest;
import static br.com.foodwise.factory.RequestFactory.buildValidRegisterCustomerRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        customerProfileRequest = buildValidCustomerRequest();
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
        String email = "test@example.com";
        CustomerProfile customerProfile = new CustomerProfile();
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
        String email = "nonexistent@example.com";
        when(customerProfileRepository.findByUserEmail(email))
                .thenReturn(java.util.Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class, () ->
                        customerProfileService.retrieveCustomerByEmail(email));

        assertEquals("Customer not found with email: " + email, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCustomerProfileRequestIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
                customerProfileService.convertToCustomerProfileEntity(null));
    }

}