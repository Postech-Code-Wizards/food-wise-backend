package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.domain.entities.RestaurantProfile;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.platform.infrastructure.rest.converter.restaurant.RestaurantProfileRequestToEntityConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConvertToRestaurantProfileEntityUseCaseTest {

    @Mock
    private RestaurantProfileRequestToEntityConverter restaurantProfileRequestToEntityConverter;

    @InjectMocks
    private ConvertToRestaurantProfileEntityUseCase convertToRestaurantProfileEntityUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnRestaurantProfileWhenConversionIsSuccessful() {
        RestaurantProfileRequest request = Instancio.create(RestaurantProfileRequest.class);
        RestaurantProfile expectedRestaurant = Instancio.create(RestaurantProfile.class);

        when(restaurantProfileRequestToEntityConverter.convert(request)).thenReturn(expectedRestaurant);

        RestaurantProfile result = convertToRestaurantProfileEntityUseCase.execute(request);

        assertNotNull(result);
        assertEquals(expectedRestaurant, result);
        verify(restaurantProfileRequestToEntityConverter, times(1)).convert(request);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenConversionFails() {
        RestaurantProfileRequest request = Instancio.create(RestaurantProfileRequest.class);

        when(restaurantProfileRequestToEntityConverter.convert(request)).thenReturn(null);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            convertToRestaurantProfileEntityUseCase.execute(request);
        });

        assertEquals("RESTAURANT_PROFILE_EXCEPTION", exception.getMessage());
        verify(restaurantProfileRequestToEntityConverter, times(1)).convert(request);
    }

}