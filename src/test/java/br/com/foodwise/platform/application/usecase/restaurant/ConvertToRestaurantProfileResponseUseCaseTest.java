package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.infrastructure.rest.converter.restaurant.RestaurantProfileEntityToResponseConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ConvertToRestaurantProfileResponseUseCaseTest {

    @Mock
    private RestaurantProfileEntityToResponseConverter restaurantProfileEntityToResponseConverter;

    @InjectMocks
    private ConvertToRestaurantProfileResponseUseCase convertToRestaurantProfileResponseUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnRestaurantProfileWhenConversionIsSuccessful() {
        RestaurantProfileResponse expectedRestaurantProfileResponse = Instancio.create(RestaurantProfileResponse.class);

        when(restaurantProfileEntityToResponseConverter.convert(any())).thenReturn(expectedRestaurantProfileResponse);

        RestaurantProfileResponse result = convertToRestaurantProfileResponseUseCase.execute(any());

        assertNotNull(result);
        assertEquals(expectedRestaurantProfileResponse, result);
        verify(restaurantProfileEntityToResponseConverter, times(1)).convert(any());
    }
    
}