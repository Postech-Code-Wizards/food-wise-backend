package br.com.foodwise.service;

import br.com.foodwise.model.entities.RestaurantProfile;
import br.com.foodwise.model.entities.User;
import br.com.foodwise.model.entities.enums.UserType;
import br.com.foodwise.model.repositories.RestaurantProfileRepository;
import br.com.foodwise.rest.converter.restaurant.RestaurantProfileEntityToResponseConverter;
import br.com.foodwise.rest.converter.restaurant.RestaurantProfileRequestToEntityConverter;
import br.com.foodwise.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import br.com.foodwise.rest.dtos.response.RestaurantProfileResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.foodwise.factory.RequestFactory.buildValidRegisterRestaurantRequest;
import static br.com.foodwise.factory.RequestFactory.buildValidRestaurantRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantProfileServiceTest {

    @Mock
    private RestaurantProfileRepository restaurantProfileRepository;

    @Mock
    private RestaurantProfileRequestToEntityConverter restaurantProfileRequestToEntityConverter;

    @Mock
    private RestaurantProfileEntityToResponseConverter restaurantProfileEntityToResponseConverter;

    @Mock
    private UserService userService;

    @InjectMocks
    private RestaurantProfileService restaurantProfileService;

    private RestaurantProfileRequest restaurantProfileRequest;

    @BeforeEach
    void setUp() {
        restaurantProfileRequest = buildValidRestaurantRequest();
    }

    @Test
    void shouldRegisterRestaurantSuccessfully() {
        var registerRestaurantRequest = buildValidRegisterRestaurantRequest();
        var userRequest = registerRestaurantRequest.getUser();
        var user = new User();
        when(userService.createUser(userRequest.getEmail(),
                userRequest.getPassword(),
                UserType.RESTAURANT_OWNER))
                .thenReturn(user);

        var restaurantEntity = new RestaurantProfile();
        when(restaurantProfileRequestToEntityConverter.convert(any()))
                .thenReturn(restaurantEntity);

        restaurantProfileService.registerRestaurant(registerRestaurantRequest);

        verify(restaurantProfileRepository).save(restaurantEntity);
    }

    @Test
    void shouldRetrieveRestaurantByBusinessNameSuccessfully() {
        var businessName = "My Restaurant";
        var restaurantProfile = new RestaurantProfile();

        when(restaurantProfileRepository.findByBusinessName(businessName))
                .thenReturn(java.util.Optional.of(restaurantProfile));
        when(restaurantProfileEntityToResponseConverter.convert(restaurantProfile))
                .thenReturn(new RestaurantProfileResponse());

        RestaurantProfileResponse response = restaurantProfileService.retrieveRestaurantByBusinessName(businessName);

        assertNotNull(response);
    }

    @Test
    void shouldRetrieveRestaurantByEmailSuccessfully() {
        String email = "test@example.com";
        RestaurantProfile restaurantProfile = new RestaurantProfile();
        when(restaurantProfileRepository.findByUserEmail(email))
                .thenReturn(java.util.Optional.of(restaurantProfile));
        when(restaurantProfileEntityToResponseConverter.convert(restaurantProfile))
                .thenReturn(new RestaurantProfileResponse());

        RestaurantProfileResponse response = restaurantProfileService.retrieveRestaurantByEmail(email);

        assertNotNull(response);
    }

    @Test
    void shouldThrowExceptionWhenRestaurantConversionFails() {
        when(restaurantProfileRequestToEntityConverter.convert(any()))
                .thenReturn(null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> restaurantProfileService
                        .convertToRestaurantProfileEntity(restaurantProfileRequest));

        assertEquals("Restaurant profile conversion failed.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenRestaurantNotFoundByBusinessName() {
        String businessName = "NonExistentRestaurant";
        when(restaurantProfileRepository.findByBusinessName(businessName))
                .thenReturn(java.util.Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
            restaurantProfileService.retrieveRestaurantByBusinessName(businessName));
    }

    @Test
    void shouldThrowExceptionWhenRestaurantNotFoundByEmail() {
        String email = "nonexistent@example.com";
        when(restaurantProfileRepository.findByUserEmail(email))
                .thenReturn(java.util.Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                restaurantProfileService.retrieveRestaurantByEmail(email));
    }

    @Test
    void shouldThrowExceptionWhenRestaurantProfileRequestIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
            restaurantProfileService.convertToRestaurantProfileEntity(null));
    }

}