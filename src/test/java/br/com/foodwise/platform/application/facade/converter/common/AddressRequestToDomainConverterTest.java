package br.com.foodwise.platform.application.facade.converter.common;

import br.com.foodwise.platform.domain.Address;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.AddressRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class AddressRequestToDomainConverterTest {

    @InjectMocks
    private AddressRequestToDomainConverter addressRequestToDomainConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Must convert AddressRequest to Address correctly")
    void convert_ShouldConvertAddressRequestToAddress() {

        AddressRequest source = Instancio.create(AddressRequest.class);

        Address result = addressRequestToDomainConverter.convert(source);

        assertNotNull(result.getId());
        assertEquals(source.getStreet(), result.getStreet());
        assertEquals(source.getCity(), result.getCity());
        assertEquals(source.getState(), result.getState());
        assertEquals(source.getNeighborhood(), result.getNeighborhood());
        assertEquals(source.getPostalCode(), result.getPostalCode());
        assertEquals(source.getCountry(), result.getCountry());
        assertEquals(source.getLatitude(), result.getLatitude());
        assertEquals(source.getLongitude(), result.getLongitude());
        assertNull(result.getCreatedAt());
        assertNull(result.getUpdatedAt());
    }

    @Test
    @DisplayName("Must convert AddressRequest with null fields")
    void convert_ShouldConvertAddressRequestWithNullFields() {

        AddressRequest source = new AddressRequest();

        Address result = addressRequestToDomainConverter.convert(source);

        assertNull(result.getId());
        assertNull(result.getStreet());
        assertNull(result.getCity());
        assertNull(result.getState());
        assertNull(result.getNeighborhood());
        assertNull(result.getPostalCode());
        assertNull(result.getCountry());
        assertNull(result.getLatitude());
        assertNull(result.getLongitude());
        assertNull(result.getCreatedAt());
        assertNull(result.getUpdatedAt());
    }

}