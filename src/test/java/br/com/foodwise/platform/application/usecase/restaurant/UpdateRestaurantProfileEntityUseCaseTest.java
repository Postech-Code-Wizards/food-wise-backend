package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.gateway.RestaurantProfileGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class UpdateRestaurantProfileEntityUseCaseTest {

    @Mock
    private RestaurantProfileGateway restaurantProfileGateway;

    @InjectMocks
    private UpdateRestaurantProfileUseCase updateRestaurantProfileUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success case for Restaurant Profile Update")
    void shouldUpdateRestaurantProfileSuccessfully() {
        RestaurantProfile restaurantProfile = Instancio.create(RestaurantProfile.class);

        when(restaurantProfileGateway.findById(anyLong())).thenReturn(restaurantProfile);

        updateRestaurantProfileUseCase.execute(restaurantProfile, anyLong());

        verify(restaurantProfileGateway, times(1)).findById(anyLong());
    }

}