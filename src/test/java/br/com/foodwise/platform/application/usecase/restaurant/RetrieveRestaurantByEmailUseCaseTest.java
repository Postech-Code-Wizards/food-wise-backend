package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.gateway.entities.RestaurantProfileEntity;
import br.com.foodwise.platform.gateway.repository.RestaurantProfileRepository;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class RetrieveRestaurantByEmailUseCaseTest {

    @Mock
    private RestaurantProfileRepository restaurantProfileRepository;

    @Mock
    private ConvertToRestaurantProfileResponseUseCase convertToRestaurantProfileResponse;

    @InjectMocks
    private RetrieveRestaurantByEmailUseCase retrieveRestaurantByEmailUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldRetrieveRestaurantByEmailSuccessfully() {
        String email = "test@code-wizards.com";
        RestaurantProfileResponse restaurantProfileResponse = Instancio.create(RestaurantProfileResponse.class);

        RestaurantProfileEntity restaurantProfileEntity = new RestaurantProfileEntity();
        when(restaurantProfileRepository.findByUserEmail(email))
                .thenReturn(java.util.Optional.of(restaurantProfileEntity));
        when(convertToRestaurantProfileResponse.execute(restaurantProfileEntity))
                .thenReturn(restaurantProfileResponse);

        RestaurantProfileResponse response = retrieveRestaurantByEmailUseCase.execute(email);

        assertNotNull(response);
    }

}