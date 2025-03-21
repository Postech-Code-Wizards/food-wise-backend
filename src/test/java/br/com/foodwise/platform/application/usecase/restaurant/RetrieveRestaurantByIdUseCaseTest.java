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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class RetrieveRestaurantByIdUseCaseTest {

    @Mock
    private RestaurantProfileGateway restaurantProfileGateway;

    @InjectMocks
    private RetrieveRestaurantByIdUseCase retrieveRestaurantByIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should retrieve RestaurantProfile by ID from gateway")
    void execute_shouldRetrieveRestaurantProfile() {

        Long restaurantId = Instancio.create(Long.class);
        RestaurantProfile expectedRestaurantProfile = Instancio.create(RestaurantProfile.class);

        when(restaurantProfileGateway.findById(restaurantId)).thenReturn(expectedRestaurantProfile);

        RestaurantProfile actualRestaurantProfile = retrieveRestaurantByIdUseCase.execute(restaurantId);

        assertEquals(expectedRestaurantProfile, actualRestaurantProfile);
    }

    @Test
    @DisplayName("Should return null when RestaurantProfile is not found by ID")
    void execute_shouldReturnNull_whenNotFound() {
        Long restaurantId = Instancio.create(Long.class);

        when(restaurantProfileGateway.findById(restaurantId)).thenReturn(null);

        RestaurantProfile actualRestaurantProfile = retrieveRestaurantByIdUseCase.execute(restaurantId);

        assertNull(actualRestaurantProfile);
    }

}