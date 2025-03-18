package br.com.foodwise.platform.application.facade.converter.restaurant;

import br.com.foodwise.platform.domain.RestaurantOwner;
import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    @DisplayName("Should convert RestaurantProfile and RestaurantOwner to RestaurantProfileResponse")
    void convert_ShouldConvertProfileAndOwnerToResponse() {

        RestaurantProfile source = Instancio.create(RestaurantProfile.class);
        RestaurantOwner owner = Instancio.create(RestaurantOwner.class);

        RestaurantProfileResponse response = restaurantProfileDomainToResponseConverter.convert(source, owner);

        assertNotNull(response);
        assertEquals(source.getDescription(), response.getDescription());
        assertEquals(owner, response.getRestaurantOwner());
    }

}