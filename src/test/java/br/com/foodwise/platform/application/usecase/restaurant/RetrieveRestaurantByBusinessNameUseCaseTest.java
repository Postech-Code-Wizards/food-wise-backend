package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.gateway.entities.RestaurantProfileEntity;
import br.com.foodwise.platform.gateway.repository.RestaurantProfileRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class RetrieveRestaurantByBusinessNameUseCaseTest {

    @Mock
    private RestaurantProfileRepository restaurantProfileRepository;

    @Mock
    private ConvertToRestaurantProfileResponseUseCase convertToRestaurantProfileResponseUseCase;

    @InjectMocks
    private RetrieveRestaurantByBusinessNameUseCase retrieveRestaurantByBusinessNameUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldRetrieveRestaurantByBusinessNameSuccessfully() {
        var businessName = "My Restaurant";
        var restaurantProfile = new RestaurantProfileEntity();

        when(restaurantProfileRepository.findByBusinessName(businessName))
                .thenReturn(java.util.Optional.of(restaurantProfile));
        when(convertToRestaurantProfileResponseUseCase.execute(restaurantProfile))
                .thenReturn(new RestaurantProfileResponse());

        RestaurantProfileResponse response = retrieveRestaurantByBusinessNameUseCase.execute(businessName);

        assertNotNull(response);
    }

    @Test
    void shouldThrowExceptionWhenRestaurantNotFoundByBusinessName() {
        String businessName = "NonExistentRestaurant";
        when(restaurantProfileRepository.findByBusinessName(businessName))
                .thenReturn(java.util.Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                retrieveRestaurantByBusinessNameUseCase.execute(businessName));
    }

}