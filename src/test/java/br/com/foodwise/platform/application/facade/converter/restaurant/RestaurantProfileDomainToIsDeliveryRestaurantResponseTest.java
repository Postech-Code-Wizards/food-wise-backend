package br.com.foodwise.platform.application.facade.converter.restaurant;

import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.IsDeliveryRestaurantResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RestaurantProfileDomainToIsDeliveryRestaurantResponseTest {

    @InjectMocks
    private RestaurantProfileDomainToIsDeliveryRestaurantResponse restaurantProfileDomainToIsDeliveryRestaurantResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should map RestaurantProfile to IsDeliveryRestaurantResponse using Instancio")
    void convert_shouldMapRestaurantProfileToIsDeliveryRestaurantResponse_usingInstancio() {

        RestaurantProfile restaurantProfile = Instancio.create(RestaurantProfile.class);

        IsDeliveryRestaurantResponse response = restaurantProfileDomainToIsDeliveryRestaurantResponse.convert(restaurantProfile);

        assertNotNull(response);
        assertEquals(restaurantProfile.getId(), response.getId());
    }

}