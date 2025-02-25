package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.domain.entities.RestaurantProfile;
import br.com.foodwise.platform.domain.repository.RestaurantProfileRepository;
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

        RestaurantProfile restaurantProfile = new RestaurantProfile();
        when(restaurantProfileRepository.findByUserEmail(email))
                .thenReturn(java.util.Optional.of(restaurantProfile));
        when(convertToRestaurantProfileResponse.execute(restaurantProfile))
                .thenReturn(restaurantProfileResponse);

        RestaurantProfileResponse response = retrieveRestaurantByEmailUseCase.execute(email);

        assertNotNull(response);
    }

}