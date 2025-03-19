package br.com.foodwise.platform.application.facade;

import br.com.foodwise.platform.application.facade.converter.common.UserRequestToDomainConverter;
import br.com.foodwise.platform.application.facade.converter.restaurant.RestaurantOwnerRequestToDomainConverter;
import br.com.foodwise.platform.application.facade.converter.restaurant.RestaurantProfileDomainToIsDeliveryRestaurantResponse;
import br.com.foodwise.platform.application.facade.converter.restaurant.RestaurantProfileDomainToResponseConverter;
import br.com.foodwise.platform.application.facade.converter.restaurant.RestaurantProfileRequestToDomainConverter;
import br.com.foodwise.platform.application.usecase.restaurant.DeleteRestaurantProfileUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.RegisterRestaurantOwnerUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.RegisterRestaurantUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.RetrieveRestaurantByBusinessNameUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.RetrieveRestaurantByEmailUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.RetrieveRestaurantByIdUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.RetrieveRestaurantOwnerUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.UpdateRestaurantOwnerUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.UpdateRestaurantProfileUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.UpdateRestaurantUserEmailUseCase;
import br.com.foodwise.platform.domain.RestaurantOwner;
import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RegisterRestaurantRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.IsDeliveryRestaurantResponse;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantProfileFacadeTest {

    @Mock
    private DeleteRestaurantProfileUseCase deleteRestaurantProfileUseCase;

    @Mock
    private RegisterRestaurantUseCase registerRestaurantUseCase;

    @Mock
    private RegisterRestaurantOwnerUseCase registerRestaurantOwnerUseCase;

    @Mock
    private UpdateRestaurantUserEmailUseCase updateRestaurantUserEmailUseCase;

    @Mock
    private RetrieveRestaurantByBusinessNameUseCase retrieveRestaurantByBusinessNameUseCase;

    @Mock
    private RetrieveRestaurantByEmailUseCase retrieveRestaurantByEmailUseCase;

    @Mock
    private UpdateRestaurantProfileUseCase updateRestaurantProfileUseCase;

    @Mock
    private RetrieveRestaurantOwnerUseCase retrieveRestaurantOwnerUseCase;

    @Mock
    private UpdateRestaurantOwnerUseCase updateRestaurantOwnerUseCase;

    @Mock
    private RestaurantProfileRequestToDomainConverter restaurantProfileRequestToDomainConverter;

    @Mock
    private RestaurantProfileDomainToResponseConverter restaurantProfileDomainToResponseConverter;

    @Mock
    private RestaurantOwnerRequestToDomainConverter restaurantOwnerRequestToDomainConverter;

    @Mock
    private UserRequestToDomainConverter userRequestToDomainConverter;

    @Mock
    private RetrieveRestaurantByIdUseCase retrieveRestaurantByIdUseCase;

    @Mock
    private RestaurantProfileDomainToIsDeliveryRestaurantResponse restaurantProfileDomainToIsDeliveryRestaurantResponse;

    @InjectMocks
    private RestaurantProfileFacade restaurantProfileFacade;

    private RegisterRestaurantRequest registerRestaurantRequest;
    private RestaurantProfile restaurantProfile;
    private RestaurantProfileRequest restaurantProfileRequest;
    private RestaurantOwner restaurantOwner;
    private UserRequest userRequest;
    private User user;
    private RestaurantProfileResponse restaurantProfileResponse;

    @BeforeEach
    void setUp() {
        registerRestaurantRequest = Instancio.create(RegisterRestaurantRequest.class);
        restaurantOwner = Instancio.create(RestaurantOwner.class);
        restaurantProfile = Instancio.create(RestaurantProfile.class);
        restaurantProfileRequest = Instancio.create(RestaurantProfileRequest.class);
        userRequest = Instancio.create(UserRequest.class);
        user = Instancio.create(User.class);
        restaurantProfileResponse = Instancio.create(RestaurantProfileResponse.class);
    }

    @Test
    @DisplayName("Must call service to register a restaurant successfully")
    void registerRestaurant_ShouldCallRegisterRestaurantUseCase() {
        when(userRequestToDomainConverter.convert(registerRestaurantRequest.getUser())).thenReturn(user);
        when(restaurantProfileRequestToDomainConverter.convert(registerRestaurantRequest.getRestaurant(), user)).thenReturn(restaurantProfile);
        when(registerRestaurantUseCase.execute(restaurantProfile)).thenReturn(restaurantProfile);
        when(restaurantOwnerRequestToDomainConverter.convert(registerRestaurantRequest.getOwner(), restaurantProfile.getUser())).thenReturn(restaurantOwner);

        restaurantProfileFacade.registerRestaurant(registerRestaurantRequest);

        verify(registerRestaurantUseCase, times(1)).execute(restaurantProfile);
        verify(registerRestaurantOwnerUseCase, times(1)).execute(restaurantOwner);
    }

    @Test
    @DisplayName("Must call service to update email a restaurant successfully")
    void updateRestaurantUserEmail_ShouldCallUpdateRestaurantUserEmailUseCase() {
        when(userRequestToDomainConverter.convert(userRequest)).thenReturn(user);
        restaurantProfileFacade.updateRestaurantUserEmail(userRequest, 1L);

        verify(updateRestaurantUserEmailUseCase, times(1)).execute(user, 1L);
    }

    @Test
    @DisplayName("Must call service to retrieve by name a restaurant successfully")
    void retrieveRestaurantByBusinessName_ShouldCallRetrieveRestaurantByBusinessNameUseCase() {
        when(retrieveRestaurantByBusinessNameUseCase.execute("Test Restaurant")).thenReturn(restaurantProfile);
        when(restaurantProfileDomainToResponseConverter.convert(restaurantProfile)).thenReturn(restaurantProfileResponse);

        var result = restaurantProfileFacade.retrieveRestaurantByBusinessName("Test Restaurant");

        assertNotNull(result);
        assertEquals(result, restaurantProfileResponse);
        verify(retrieveRestaurantByBusinessNameUseCase, times(1)).execute("Test Restaurant");
    }

    @Test
    @DisplayName("Must call service to retrieve by email a restaurant successfully")
    void retrieveRestaurantByEmail_ShouldCallRetrieveRestaurantByEmailUseCase() {
        when(retrieveRestaurantByEmailUseCase.execute(userRequest.getEmail())).thenReturn(restaurantProfile);
        when(retrieveRestaurantOwnerUseCase.execute(restaurantProfile.getId())).thenReturn(restaurantOwner);
        when(restaurantProfileDomainToResponseConverter.convert(restaurantProfile, restaurantOwner)).thenReturn(restaurantProfileResponse);

        var result = restaurantProfileFacade.retrieveRestaurantByEmail(userRequest.getEmail());

        assertNotNull(result);
        assertEquals(result, restaurantProfileResponse);
        verify(retrieveRestaurantByEmailUseCase, times(1)).execute(userRequest.getEmail());
        verify(retrieveRestaurantOwnerUseCase, times(1)).execute(restaurantProfile.getId());
    }

    @Test
    @DisplayName("Must call service to update a restaurant successfully")
    void updateRestaurantProfile_ShouldCallUpdateRestaurantProfileUseCase() {
        when(restaurantProfileRequestToDomainConverter.convert(restaurantProfileRequest)).thenReturn(restaurantProfile);

        restaurantProfileFacade.updateRestaurantProfile(restaurantProfileRequest, 1L);

        verify(updateRestaurantProfileUseCase, times(1)).execute(restaurantProfile, 1L);
    }

    @Test
    @DisplayName("Must call service to delete a restaurant successfully")
    void delete_ShouldCallDeleteRestaurantProfileUseCase() {
        restaurantProfileFacade.delete(1L);

        verify(deleteRestaurantProfileUseCase, times(1)).execute(1L);
    }

    @Test
    @DisplayName("Must call service to update restaurant owner successfully")
    void updateRestaurantOwner_ShouldCallUpdateRestaurantOwnerUseCase() {

        var registerRestaurantOwnerRequestUpdate = registerRestaurantRequest;
        Long userId = 1L;
        var restaurantOwnerUpdate = restaurantOwner;

        when(restaurantOwnerRequestToDomainConverter.convert(registerRestaurantOwnerRequestUpdate.getOwner())).thenReturn(restaurantOwnerUpdate);

        restaurantProfileFacade.updateRestaurantOwner(registerRestaurantOwnerRequestUpdate.getOwner(), userId);

        verify(restaurantOwnerRequestToDomainConverter, times(1)).convert(registerRestaurantOwnerRequestUpdate.getOwner());
        verify(updateRestaurantOwnerUseCase, times(1)).execute(restaurantOwnerUpdate, userId);
    }

    @Test
    @DisplayName("Should retrieve restaurant by ID and convert to IsDeliveryRestaurantResponse")
    void retrieveRestaurantById_shouldRetrieveAndConvert() {

        Long restaurantId = Instancio.create(Long.class);
        RestaurantProfile restaurantProfile = Instancio.create(RestaurantProfile.class);
        IsDeliveryRestaurantResponse expectedResponse = Instancio.create(IsDeliveryRestaurantResponse.class);

        when(retrieveRestaurantByIdUseCase.execute(restaurantId)).thenReturn(restaurantProfile);
        when(restaurantProfileDomainToIsDeliveryRestaurantResponse.convert(restaurantProfile)).thenReturn(expectedResponse);

        IsDeliveryRestaurantResponse actualResponse = restaurantProfileFacade.retrieveRestaurantById(restaurantId);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

}