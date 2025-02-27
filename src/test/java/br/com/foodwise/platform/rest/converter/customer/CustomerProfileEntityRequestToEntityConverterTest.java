package br.com.foodwise.platform.rest.converter.customer;

import br.com.foodwise.platform.gateway.entities.AddressEntity;
import br.com.foodwise.platform.gateway.entities.PhoneEntity;
import br.com.foodwise.platform.infrastructure.rest.converter.common.AddressRequestToEntityConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.common.PhoneRequestToEntityConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.customer.CustomerProfileRequestToEntityConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.CustomerProfileRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.foodwise.platform.factory.EntityFactory.buildAddress;
import static br.com.foodwise.platform.factory.EntityFactory.buildPhone;
import static br.com.foodwise.platform.factory.RequestFactory.buildCustomerProfileRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerProfileEntityRequestToEntityConverterTest {
    @Mock
    private AddressRequestToEntityConverter addressRequestToEntityConverter;

    @Mock
    private PhoneRequestToEntityConverter phoneRequestToEntityConverter;

    @InjectMocks
    private CustomerProfileRequestToEntityConverter converter;

    @Test
    void convert_shouldMapCustomerProfileRequestToProfileEntity() {
        var source = buildCustomerProfileRequest();

        when(addressRequestToEntityConverter.convert(any()))
                .thenReturn(buildAddress());

        when(phoneRequestToEntityConverter.convert(any()))
                .thenReturn(buildPhone());

        var customerProfile = converter.convert(source);

        verify(addressRequestToEntityConverter).convert(source.getAddress());
        verify(phoneRequestToEntityConverter).convert(source.getPhone());

        assertNotNull(customerProfile);
        assertNotNull(customerProfile.getAddressEntity());
        assertNotNull(customerProfile.getPhoneEntity());

        assertEquals(customerProfile.getAddressEntity().getClass(), AddressEntity.class);
        assertEquals(customerProfile.getPhoneEntity().getClass(), PhoneEntity.class);
    }

    @Test
    void convert_shouldHandleNullFieldsGracefully() {
        var sourceWithNullFields = new CustomerProfileRequest();

        var result = converter.convert(sourceWithNullFields);

        assertNotNull(result);
        assertNull(result.getAddressEntity());
        assertNull(result.getPhoneEntity());
        assertNull(result.getUserEntity());
        assertNull(result.getFirstName());
    }

}
