package br.com.foodwise.platform.application.service;
import br.com.foodwise.platform.application.usecase.restaurant.*;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RegisterRestaurantRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
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

    private RegisterRestaurantRequest registerRestaurantRequest;
    private UserRequest userRequest;
    private RestaurantProfileResponse restaurantProfileResponse;
    private RestaurantProfileRequest restaurantProfileRequestUpdate;

    @BeforeEach
    void setUp() {
        registerRestaurantRequest = Instancio.create(RegisterRestaurantRequest.class);
        userRequest = Instancio.create(UserRequest.class);
        restaurantProfileResponse = Instancio.create(RestaurantProfileResponse.class);
        restaurantProfileRequestUpdate = Instancio.create(RestaurantProfileRequest.class);
    }

    @Test
    void registerRestaurant_ShouldCallRegisterRestaurantUseCase() {
        restaurantProfileService.registerRestaurant(registerRestaurantRequest);

        verify(registerRestaurantUseCase, times(1)).execute(registerRestaurantRequest);
    }

    @Test
    void updateRestaurantUserEmail_ShouldCallUpdateRestaurantUserEmailUseCase() {
        restaurantProfileService.updateRestaurantUserEmail(userRequest, 1L);

        verify(updateRestaurantUserEmailUseCase, times(1)).execute(userRequest, 1L);
    }

    @Test
    void retrieveRestaurantByBusinessName_ShouldCallRetrieveRestaurantByBusinessNameUseCase() {
        when(retrieveRestaurantByBusinessNameUseCase.execute("Test Restaurant")).thenReturn(restaurantProfileResponse);

        RestaurantProfileResponse result = restaurantProfileService.retrieveRestaurantByBusinessName("Test Restaurant");

        assertEquals(restaurantProfileResponse, result);
        verify(retrieveRestaurantByBusinessNameUseCase, times(1)).execute("Test Restaurant");
    }

    @Test
    void retrieveRestaurantByEmail_ShouldCallRetrieveRestaurantByEmailUseCase() {
        when(retrieveRestaurantByEmailUseCase.execute("test@example.com")).thenReturn(restaurantProfileResponse);

        RestaurantProfileResponse result = restaurantProfileService.retrieveRestaurantByEmail("test@example.com");

        assertEquals(restaurantProfileResponse, result);
        verify(retrieveRestaurantByEmailUseCase, times(1)).execute("test@example.com");
    }

    @Test
    void updateRestaurantProfile_ShouldCallUpdateRestaurantProfileUseCase() {
        restaurantProfileService.updateRestaurantProfile(restaurantProfileRequestUpdate, 1L);

        verify(updateRestaurantProfileUseCase, times(1)).execute(restaurantProfileRequestUpdate, 1L);
    }

    @Test
    void delete_ShouldCallDeleteRestaurantProfileUseCase() {
        restaurantProfileService.delete(1L);

        verify(deleteRestaurantProfileUseCase, times(1)).execute(1L);
    }
}