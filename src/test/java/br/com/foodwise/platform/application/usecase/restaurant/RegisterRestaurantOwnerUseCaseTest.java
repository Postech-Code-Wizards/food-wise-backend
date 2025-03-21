package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.domain.RestaurantOwner;
import br.com.foodwise.platform.gateway.RestaurantOwnerGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegisterRestaurantOwnerUseCaseTest {

    @Mock
    private RestaurantOwnerGateway restaurantOwnerGateway;

    @InjectMocks
    private RegisterRestaurantOwnerUseCase registerRestaurantOwnerUseCase;

    @Test
    @DisplayName("Should save RestaurantOwner successfully")
    void execute_ShouldSaveSuccessfully() {

        RestaurantOwner restaurantOwner = Instancio.create(RestaurantOwner.class);

        registerRestaurantOwnerUseCase.execute(restaurantOwner);

        verify(restaurantOwnerGateway, times(1)).save(restaurantOwner);
    }
}