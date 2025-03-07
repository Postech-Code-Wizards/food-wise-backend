package br.com.foodwise.platform.application.service;
import br.com.foodwise.platform.application.usecase.restaurant.*;
import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.domain.User;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantProfileServiceTest {

    @Mock
    private DeleteRestaurantProfileUseCase deleteRestaurantProfileUseCase;

    @Mock
    private RegisterRestaurantUseCase registerRestaurantUseCase;

    @Mock
    private UpdateRestaurantUserEmailUseCase updateRestaurantUserEmailUseCase;

    @Mock
    private RetrieveRestaurantByBusinessNameUseCase retrieveRestaurantByBusinessNameUseCase;

    @Mock
    private RetrieveRestaurantByEmailUseCase retrieveRestaurantByEmailUseCase;

    @Mock
    private UpdateRestaurantProfileUseCase updateRestaurantProfileUseCase;

    @InjectMocks
    private RestaurantProfileService restaurantProfileService;

    private RestaurantProfile restaurantProfile;
    private User user;

    @BeforeEach
    void setUp() {
        restaurantProfile = Instancio.create(RestaurantProfile.class);
        user = Instancio.create(User.class);
    }

    @Test
    @DisplayName("Must call service to register a restaurant successfully")
    void registerRestaurant_ShouldCallRegisterRestaurantUseCase() {
        restaurantProfileService.registerRestaurant(restaurantProfile);

        verify(registerRestaurantUseCase, times(1)).execute(restaurantProfile);
    }

    @Test
    @DisplayName("Must call service to update email a restaurant successfully")
    void updateRestaurantUserEmail_ShouldCallUpdateRestaurantUserEmailUseCase() {
        restaurantProfileService.updateRestaurantUserEmail(user, 1L);

        verify(updateRestaurantUserEmailUseCase, times(1)).execute(user, 1L);
    }

    @Test
    @DisplayName("Must call service to retrieve by name a restaurant successfully")
    void retrieveRestaurantByBusinessName_ShouldCallRetrieveRestaurantByBusinessNameUseCase() {
        when(retrieveRestaurantByBusinessNameUseCase.execute("Test Restaurant")).thenReturn(restaurantProfile);

        var result = restaurantProfileService.retrieveRestaurantByBusinessName("Test Restaurant");

        assertEquals(restaurantProfile, result);
        verify(retrieveRestaurantByBusinessNameUseCase, times(1)).execute("Test Restaurant");
    }

    @Test
    @DisplayName("Must call service to retrieve by email a restaurant successfully")
    void retrieveRestaurantByEmail_ShouldCallRetrieveRestaurantByEmailUseCase() {
        when(retrieveRestaurantByEmailUseCase.execute()).thenReturn(restaurantProfile);

        var result = restaurantProfileService.retrieveRestaurantByEmail();

        assertEquals(restaurantProfile, result);
        verify(retrieveRestaurantByEmailUseCase, times(1)).execute();
    }

    @Test
    @DisplayName("Must call service to update a restaurant successfully")
    void updateRestaurantProfile_ShouldCallUpdateRestaurantProfileUseCase() {
        restaurantProfileService.updateRestaurantProfile(restaurantProfile, 1L);

        verify(updateRestaurantProfileUseCase, times(1)).execute(restaurantProfile, 1L);
    }

    @Test
    @DisplayName("Must call service to delete a restaurant successfully")
    void delete_ShouldCallDeleteRestaurantProfileUseCase() {
        restaurantProfileService.delete(1L);

        verify(deleteRestaurantProfileUseCase, times(1)).execute(1L);
    }
}