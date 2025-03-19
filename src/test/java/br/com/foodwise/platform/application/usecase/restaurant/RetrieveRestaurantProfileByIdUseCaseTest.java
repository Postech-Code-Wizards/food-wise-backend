package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.gateway.RestaurantProfileGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveRestaurantProfileByIdUseCaseTest {

    @Mock
    private RestaurantProfileGateway restaurantProfileGateway;

    @InjectMocks
    private RetrieveRestaurantProfileByIdUseCase useCase;

    @Test
    @DisplayName("Should retrieve restaurant profile by id successfully")
    void retrieveById_ShouldRetrieveRestaurantProfile() {

        Long restaurantProfileId = Instancio.create(Long.class);
        RestaurantProfile restaurantProfile = Instancio.create(RestaurantProfile.class);

        when(restaurantProfileGateway.findById(restaurantProfileId)).thenReturn(restaurantProfile);

        RestaurantProfile result = useCase.retrieveById(restaurantProfileId);

        assertNotNull(result);
        assertEquals(restaurantProfile, result);
    }

}