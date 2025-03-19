package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.Address;
import br.com.foodwise.platform.gateway.database.jpa.entities.AddressEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class AddressEntityToDomainConverterTest {

    @InjectMocks
    private AddressEntityToDomainConverter addressEntityToDomainConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test conversion of AddressEntity to Address domain object ")
    void testConvertAddressEntityToDomain() {

        AddressEntity source = Instancio.create(AddressEntity.class);

        Address domain = addressEntityToDomainConverter.convert(source);

        assertNotNull(domain);
        assertEquals(source.getId(), domain.getId());
        assertEquals(source.getStreet(), domain.getStreet());
        assertEquals(source.getCity(), domain.getCity());
        assertEquals(source.getState(), domain.getState());
        assertEquals(source.getNeighborhood(), domain.getNeighborhood());
        assertEquals(source.getPostalCode(), domain.getPostalCode());
        assertEquals(source.getCountry(), domain.getCountry());
        assertEquals(source.getLatitude(), domain.getLatitude());
        assertEquals(source.getLongitude(), domain.getLongitude());
        assertEquals(source.getCreatedAt(), domain.getCreatedAt());
        assertEquals(source.getUpdatedAt(), domain.getUpdatedAt());
    }

}