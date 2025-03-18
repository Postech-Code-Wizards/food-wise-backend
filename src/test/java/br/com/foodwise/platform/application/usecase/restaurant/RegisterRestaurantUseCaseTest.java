package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.gateway.RestaurantProfileGateway;
import br.com.foodwise.platform.gateway.UserGateway;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.BusinessException;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RegisterRestaurantUseCaseTest {

    @Mock
    private RestaurantProfileGateway restaurantProfileGateway;

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private RegisterRestaurantUseCase registerRestaurantUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Must register restaurant successfully")
    void shouldRegisterRestaurantSuccessfully() {

        var restaurantProfile = Instancio.create(RestaurantProfile.class);

        registerRestaurantUseCase.execute(restaurantProfile);

        verify(restaurantProfileGateway).save(restaurantProfile);
    }

    @Test
    @DisplayName("Must throw exception when registering restaurant")
    void shouldRegisterRestaurantException() {

        var restaurantProfile = Instancio.create(RestaurantProfile.class);

        when(userGateway.existsByEmail(restaurantProfile.getUser().getEmail()))
                .thenReturn(true);

        assertThrows(BusinessException.class, () ->
                registerRestaurantUseCase.execute(restaurantProfile));
    }

}