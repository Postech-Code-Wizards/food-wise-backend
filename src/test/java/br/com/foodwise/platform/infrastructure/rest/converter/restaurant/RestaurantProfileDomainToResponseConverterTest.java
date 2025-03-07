package br.com.foodwise.platform.infrastructure.rest.converter.restaurant;

import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantProfileDomainToResponseConverterTest {

    @InjectMocks
    private RestaurantProfileDomainToResponseConverter restaurantProfileDomainToResponseConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should convert RestaurantProfile to RestaurantProfileResponse correctly")
    void convert_ShouldConvertRestaurantProfileToRestaurantProfileResponse() {

        RestaurantProfile source = Instancio.create(RestaurantProfile.class);

        RestaurantProfileResponse result = restaurantProfileDomainToResponseConverter.convert(source);

        assertNotNull(result);
        assertEquals(source.getBusinessName(), result.getBusinessName());
        assertEquals(source.getDescription(), result.getDescription());
    }

    @Test
    @DisplayName("Should convert RestaurantProfile with null fields")
    void convert_ShouldConvertRestaurantProfileWithNullFields() {

        RestaurantProfile source = Instancio.of(RestaurantProfile.class)
                .set(field(RestaurantProfile::getId), null)
                .set(field(RestaurantProfile::getBusinessName), null)
                .set(field(RestaurantProfile::getDescription), null)
                .create();

        RestaurantProfileResponse result = restaurantProfileDomainToResponseConverter.convert(source);

        assertNotNull(result);
        assertNull(result.getBusinessName());
        assertNull(result.getDescription());

    }

}