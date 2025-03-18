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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveRestaurantOwnerUseCaseTest {

    @Mock
    private RestaurantOwnerGateway restaurantOwnerGateway;

    @InjectMocks
    private RetrieveRestaurantOwnerUseCase retrieveRestaurantOwnerUseCase;

    @Test
    @DisplayName("Should retrieve RestaurantOwner by ID from gateway using Instancio")
    void execute_shouldRetrieveRestaurantOwner_usingInstancio() {

        Long restaurantOwnerId = Instancio.create(Long.class);
        RestaurantOwner expectedRestaurantOwner = Instancio.create(RestaurantOwner.class);

        when(restaurantOwnerGateway.findById(restaurantOwnerId)).thenReturn(expectedRestaurantOwner);

        RestaurantOwner actualRestaurantOwner = retrieveRestaurantOwnerUseCase.execute(restaurantOwnerId);

        assertEquals(expectedRestaurantOwner, actualRestaurantOwner);
    }

    @Test
    @DisplayName("Should return null when RestaurantOwner is not found by ID")
    void execute_shouldReturnNull_whenNotFound() {

        Long restaurantOwnerId = Instancio.create(Long.class);

        when(restaurantOwnerGateway.findById(restaurantOwnerId)).thenReturn(null);

        RestaurantOwner actualRestaurantOwner = retrieveRestaurantOwnerUseCase.execute(restaurantOwnerId);

        assertNull(actualRestaurantOwner);
    }
}