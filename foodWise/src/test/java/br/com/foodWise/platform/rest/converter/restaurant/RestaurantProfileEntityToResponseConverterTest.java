package br.com.foodwise.platform.rest.converter.restaurant;

import br.com.foodwise.platform.model.entities.RestaurantProfile;
import br.com.foodwise.platform.rest.converter.restaurant.RestaurantProfileEntityToResponseConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.foodwise.platform.factory.EntityFactory.buildRestaurantProfile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class RestaurantProfileEntityToResponseConverterTest {
    private RestaurantProfileEntityToResponseConverter converter;

    @BeforeEach
    void setUp() {
        converter = new RestaurantProfileEntityToResponseConverter();
    }

    @Test
    void convert_shouldMapRestaurantProfileToResponse() {
        var source = buildRestaurantProfile();

        var response = converter.convert(source);

        assertNotNull(response);
        assertEquals(source.getAddress(), response.getAddress());
        assertEquals(source.getPhone(), response.getPhone());
        assertEquals(source.getBusinessName(), response.getBusinessName());
        assertEquals(source.getCuisineType(), response.getCuisineType());
        assertEquals(source.getDeliveryRadius(), response.getDeliveryRadius());
        assertEquals(source.getBusinessHours(), response.getBusinessHours());
        assertEquals(source.getDescription(), response.getDescription());
    }

    @Test
    void convert_shouldReturnNullWhenSourceIsNull() {
        var sourceWithNullFields = new RestaurantProfile();
        var response = converter.convert(sourceWithNullFields);

        assertNotNull(response);
        assertNull(response.getDescription());
        assertNull(response.getAddress());
        assertNull(response.getPhone());
        assertNull(response.getCuisineType());
        assertNull(response.getBusinessHours());
        assertNull(response.getBusinessName());
    }
}
