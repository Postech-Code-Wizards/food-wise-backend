package br.com.foodwise.platform.rest.converter.common;

import br.com.foodwise.platform.rest.dtos.request.register.AddressRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.foodwise.platform.factory.RequestFactory.buildAddressRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class AddressRequestToEntityConverterTest {

    private AddressRequestToEntityConverter converter;

    @BeforeEach
    void setUp() {
        converter = new AddressRequestToEntityConverter();
    }

    @Test
    void convert_shouldMapAddressRequestToAddressEntity() {
        var source = buildAddressRequest();
        var result = converter.convert(source);

        assertNotNull(result);
        assertEquals(source.getStreet(), result.getStreet());
        assertEquals(source.getCity(), result.getCity());
        assertEquals(source.getState(), result.getState());
        assertEquals(source.getCountry(), result.getCountry());
        assertEquals(source.getNeighborhood(), result.getNeighborhood());
        assertEquals(source.getPostalCode(), result.getPostalCode());
        assertEquals(source.getLatitude(), result.getLatitude());
        assertEquals(source.getLongitude(), result.getLongitude());

    }

    @Test
    void convert_shouldHandleNullFieldsGracefully() {
        var sourceWithNullFields = new AddressRequest();

        var result = converter.convert(sourceWithNullFields);

        assertNotNull(result);
        assertNull(result.getStreet());
        assertNull(result.getCity());
        assertNull(result.getState());
        assertNull(result.getLatitude());
        assertNull(result.getLongitude());
        assertNull(result.getNeighborhood());
        assertNull(result.getPostalCode());
        assertNull(result.getCountry());
    }
}
