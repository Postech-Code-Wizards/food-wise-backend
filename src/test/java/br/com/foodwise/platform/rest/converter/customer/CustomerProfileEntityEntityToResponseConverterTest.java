package br.com.foodwise.platform.rest.converter.customer;

import br.com.foodwise.platform.gateway.entities.CustomerProfileEntity;
import br.com.foodwise.platform.infrastructure.rest.converter.customer.CustomerProfileEntityToResponseConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.foodwise.platform.factory.EntityFactory.buildCustomerProfile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class CustomerProfileEntityEntityToResponseConverterTest {

    private CustomerProfileEntityToResponseConverter converter;

    @BeforeEach
    void setUp() {
        converter = new CustomerProfileEntityToResponseConverter();
    }

    @Test
    void convert_shouldMapCustomerProfileToCustomerProfileResponse() {
        var source = buildCustomerProfile();

        var result = converter.convert(source);

        assertNotNull(result);
        assertEquals(source.getFirstName(), result.getFirstName());
        assertEquals(source.getLastName(), result.getLastName());
        assertEquals(source.getPhoneEntity(), result.getPhoneEntity());
        assertEquals(source.getAddressEntity(), result.getAddressEntity());
    }

    @Test
    void convert_shouldHandleNullFieldsGracefully() {
        var sourceWithNullFields = new CustomerProfileEntity();
        var result = converter.convert(sourceWithNullFields);

        assertNotNull(result);
        assertNull(result.getFirstName());
        assertNull(result.getLastName());
        assertNull(result.getAddressEntity());
        assertNull(result.getPhoneEntity());
    }
}
