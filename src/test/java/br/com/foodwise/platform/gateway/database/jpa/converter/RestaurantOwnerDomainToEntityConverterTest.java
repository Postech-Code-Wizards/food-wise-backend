package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.RestaurantOwner;
import br.com.foodwise.platform.gateway.database.jpa.entities.RestaurantOwnerEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RestaurantOwnerDomainToEntityConverterTest {

    @InjectMocks
    private RestaurantOwnerDomainToEntityConverter converter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should convert RestaurantOwner domain to RestaurantOwnerEntity")
    void convert_ShouldConvertDomainToEntity() {

        RestaurantOwner source = Instancio.create(RestaurantOwner.class);

        RestaurantOwnerEntity result = converter.convert(source);

        assertNotNull(result);
        assertEquals(source.getId(), result.getId());
    }

}