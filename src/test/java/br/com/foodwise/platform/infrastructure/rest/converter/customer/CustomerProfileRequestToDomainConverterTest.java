package br.com.foodwise.platform.infrastructure.rest.converter.customer;

import br.com.foodwise.platform.application.facade.converter.customer.CustomerProfileRequestToDomainConverter;
import br.com.foodwise.platform.domain.Address;
import br.com.foodwise.platform.domain.CustomerProfile;
import br.com.foodwise.platform.domain.Phone;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.application.facade.converter.common.AddressRequestToDomainConverter;
import br.com.foodwise.platform.application.facade.converter.common.PhoneRequestToDomainConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.CustomerProfileRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CustomerProfileRequestToDomainConverterTest {

    @Mock
    private AddressRequestToDomainConverter addressRequestToDomainConverter;

    @Mock
    private PhoneRequestToDomainConverter phoneRequestToDomainConverter;

    @InjectMocks
    private CustomerProfileRequestToDomainConverter customerProfileRequestToDomainConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should convert CustomerProfileRequest to CustomerProfile with User")
    void convert_ShouldConvertCustomerProfileRequestToCustomerProfileWithUser() {

        CustomerProfileRequest customerProfileRequest = Instancio.create(CustomerProfileRequest.class);
        User user = Instancio.create(User.class);
        Address address = Instancio.create(Address.class);
        Phone phone = Instancio.create(Phone.class);

        when(addressRequestToDomainConverter.convert(any())).thenReturn(address);
        when(phoneRequestToDomainConverter.convert(any())).thenReturn(phone);

        CustomerProfile result = customerProfileRequestToDomainConverter.convert(customerProfileRequest, user);

        assertNull(result.getId());
        assertEquals(customerProfileRequest.getFirstName(), result.getFirstName());
        assertEquals(customerProfileRequest.getLastName(), result.getLastName());
        assertEquals(address, result.getAddress());
        assertNull(result.getCreatedAt());
        assertNull(result.getUpdatedAt());
        assertEquals(user, result.getUser());
        assertEquals(phone, result.getPhone());
    }

    @Test
    @DisplayName("Should convert CustomerProfileRequest to CustomerProfile without User")
    void convert_ShouldConvertCustomerProfileRequestToCustomerProfileWithoutUser() {

        CustomerProfileRequest customerProfileRequest = Instancio.create(CustomerProfileRequest.class);
        Address address = Instancio.create(Address.class);
        Phone phone = Instancio.create(Phone.class);

        when(addressRequestToDomainConverter.convert(any())).thenReturn(address);
        when(phoneRequestToDomainConverter.convert(any())).thenReturn(phone);

        CustomerProfile result = customerProfileRequestToDomainConverter.convert(customerProfileRequest);

        assertNull(result.getId());
        assertEquals(customerProfileRequest.getFirstName(), result.getFirstName());
        assertEquals(customerProfileRequest.getLastName(), result.getLastName());
        assertEquals(address, result.getAddress());
        assertNull(result.getCreatedAt());
        assertNull(result.getUpdatedAt());
        assertNull(result.getUser());
        assertEquals(phone, result.getPhone());
    }

}