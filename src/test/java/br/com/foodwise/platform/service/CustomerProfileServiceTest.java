package br.com.foodwise.platform.service;

import br.com.foodwise.platform.domain.entities.CustomerProfile;
import br.com.foodwise.platform.domain.entities.User;
import br.com.foodwise.platform.domain.entities.enums.UserType;
import br.com.foodwise.platform.domain.repository.CustomerProfileRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.platform.infrastructure.rest.converter.customer.CustomerProfileEntityToResponseConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.customer.CustomerProfileRequestToEntityConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.CustomerProfileRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.CustomerProfileResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.Optional;

import static br.com.foodwise.platform.factory.RequestFactory.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @InjectMocks
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

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class, () -> customerProfileService
                        .convertToCustomerProfileEntity(customerProfileRequest));

        assertEquals("CUSTOMER_PROFILE_EXCEPTION",
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
        assertThrows(ResourceNotFoundException.class, () ->
                customerProfileService.convertToCustomerProfileEntity(null));
    }

    @Test
    void deleteCustomer() {
        var id = Instancio.create(long.class);
        customerProfileService.delete(id);
        verify(userService, times(1)).delete(id, UserType.CUSTOMER);
    }

    @Test
    @DisplayName("Success case for Customer Profile Update")
    void shouldUpdateCustomerProfileSuccessfully() {
        CustomerProfileRequest customerNewData = buildCustomerProfileRequest();
        var customerProfile = buildCustomerProfileEntity();

        when(customerProfileRepository.findById(anyLong())).thenReturn(Optional.of(customerProfile));

        var customerProfileEntity = new CustomerProfile();
        customerProfileEntity.setUpdatedAt(ZonedDateTime.now());
        when(customerProfileRequestToEntityConverter.convert(any())).thenReturn(customerProfileEntity);

        customerProfileService.updateCustomerProfile(customerNewData, anyLong());

        verify(customerProfileRepository, times(1)).findById(anyLong());
        assertEquals(customerProfileEntity.getFirstName(), customerProfile.getFirstName());
        assertEquals(customerProfileEntity.getLastName(), customerProfile.getLastName());
        assertEquals(customerProfileEntity.getAddress(), customerProfile.getAddress());
        assertEquals(customerProfileEntity.getPhone(), customerProfile.getPhone());
        assertNotNull(customerProfileEntity.getUpdatedAt());
    }

    @Test
    @DisplayName("Fail case for Customer Profile Update")
    void shouldThrowExceptionForNotFindingCustomerProfile() {
        CustomerProfileRequest customerNewData = buildCustomerProfileRequest();

        long nonExistentUserId = 500000000L;

        doReturn(Optional.empty()).when(customerProfileRepository).findById(nonExistentUserId);

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> customerProfileService.updateCustomerProfile(customerNewData, nonExistentUserId)
        );

        assertEquals("CUSTOMER_DOES_NOT_EXIST", exception.getCode());

        verify(customerProfileRepository, times(1)).findById(nonExistentUserId);

        verify(customerProfileRepository, never()).save(any());
    }

}
