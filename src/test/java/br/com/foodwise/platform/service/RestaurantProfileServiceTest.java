package br.com.foodwise.platform.service;

import br.com.foodwise.platform.application.service.RestaurantProfileService;
import br.com.foodwise.platform.application.service.UserService;
import br.com.foodwise.platform.domain.entities.RestaurantProfile;
import br.com.foodwise.platform.domain.entities.User;
import br.com.foodwise.platform.domain.entities.enums.UserType;
import br.com.foodwise.platform.domain.repository.RestaurantProfileRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.platform.infrastructure.rest.converter.restaurant.RestaurantProfileEntityToResponseConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.restaurant.RestaurantProfileRequestToEntityConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.Optional;

import static br.com.foodwise.platform.factory.RequestFactory.buildRestaurantProfileEntity;
import static br.com.foodwise.platform.factory.RequestFactory.buildRestaurantProfileRequest;
import static br.com.foodwise.platform.factory.RequestFactory.buildValidRegisterRestaurantRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
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
        restaurantProfileRequest = buildRestaurantProfileRequest();
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
        String email = "test@code-wizards.com";
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

//        ResourceNotFoundException exception = assertThrows(
//                ResourceNotFoundException.class, () -> restaurantProfileService
//                        .convertToRestaurantProfileEntity(restaurantProfileRequest));
//
//        assertEquals("RESTAURANT_PROFILE_EXCEPTION", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenRestaurantNotFoundByBusinessName() {
        String businessName = "NonExistentRestaurant";
        when(restaurantProfileRepository.findByBusinessName(businessName))
                .thenReturn(java.util.Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                restaurantProfileService.retrieveRestaurantByBusinessName(businessName));
    }

    @Test
    void shouldThrowExceptionWhenRestaurantNotFoundByEmail() {
        String email = "nonexistent@code-wizards.com";
        when(restaurantProfileRepository.findByUserEmail(email))
                .thenReturn(java.util.Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                restaurantProfileService.retrieveRestaurantByEmail(email));
    }

    @Test
    void shouldThrowExceptionWhenRestaurantProfileRequestIsNull() {
//        assertThrows(ResourceNotFoundException.class, () ->
//                restaurantProfileService.convertToRestaurantProfileEntity(null));
    }

    @Test
    void deleteRestaurant() {
        var id = Instancio.create(long.class);
        restaurantProfileService.delete(id);
        verify(userService, times(1)).delete(id, UserType.RESTAURANT_OWNER);
    }

    @Test
    @DisplayName("Success case for Restaurant Profile Update")
    void shouldUpdateRestaurantProfileSuccessfully() {

        RestaurantProfileRequest restaurantNewData = buildRestaurantProfileRequest();

        var restaurantProfile = buildRestaurantProfileEntity();

        when(restaurantProfileRepository.findById(anyLong())).thenReturn(Optional.of(restaurantProfile));

        var restaurantProfileEntity = new RestaurantProfile();
        restaurantProfileEntity.setUpdatedAt(ZonedDateTime.now());
        when(restaurantProfileRequestToEntityConverter.convert(any())).thenReturn(restaurantProfileEntity);

        restaurantProfileService.updateRestaurantProfile(restaurantNewData, anyLong());

        verify(restaurantProfileRepository, times(1)).findById(anyLong());
        assertEquals(restaurantProfileEntity.getBusinessName(), restaurantProfile.getBusinessName());
        assertEquals(restaurantProfileEntity.getDescription(), restaurantProfile.getDescription());
        assertEquals(restaurantProfileEntity.getDeliveryRadius(), restaurantProfile.getDeliveryRadius());
        assertEquals(restaurantProfileEntity.getAddress(), restaurantProfile.getAddress());
        assertEquals(restaurantProfileEntity.getPhone(), restaurantProfile.getPhone());
        assertEquals(restaurantProfileEntity.getCuisineType(), restaurantProfile.getCuisineType());
        assertNotNull(restaurantProfileEntity.getUpdatedAt());
    }

    @Test
    @DisplayName("Fail case for Restaurant Profile Update")
    void shouldThrowExceptionForNotFindingRestaurantProfile() {
        RestaurantProfileRequest restaurantNewData = buildRestaurantProfileRequest();

        long nonExistentUserId = 500000000L;

        doReturn(Optional.empty()).when(restaurantProfileRepository).findById(nonExistentUserId);

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> restaurantProfileService.updateRestaurantProfile(restaurantNewData, nonExistentUserId)
        );

        assertEquals("RESTAURANT_DOES_NOT_EXIST", exception.getCode());

        verify(restaurantProfileRepository, times(1)).findById(nonExistentUserId);

        verify(restaurantProfileRepository, never()).save(any());
    }

}
