package br.com.foodwise.platform.application.facade;

import br.com.foodwise.platform.application.facade.converter.common.UserRequestToDomainConverter;
import br.com.foodwise.platform.application.facade.converter.restaurant.RestaurantOwnerRequestToDomainConverter;
import br.com.foodwise.platform.application.facade.converter.restaurant.RestaurantProfileDomainToResponseConverter;
import br.com.foodwise.platform.application.facade.converter.restaurant.RestaurantProfileRequestToDomainConverter;
import br.com.foodwise.platform.domain.RestaurantOwner;
import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RegisterRestaurantOwnerRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RegisterRestaurantRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.IsDeliveryRestaurantResponse;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;
import jakarta.transaction.Transactional;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
/*@Sql(scripts = {"/restaurant/db_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)*/
@Sql(scripts = {"/restaurant/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class RestaurantProfileFacadeIT {


    @Autowired
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
        UserRequest user1 = Instancio.create(UserRequest.class);
        RestaurantProfileRequest restaurantProfile1 = Instancio.create(RestaurantProfileRequest.class);
        RegisterRestaurantOwnerRequest registerRestaurantOwnerRequest1 = Instancio.create(RegisterRestaurantOwnerRequest.class);

        RegisterRestaurantRequest registerRestaurantRequest = new RegisterRestaurantRequest();
        registerRestaurantRequest.setUser(user1);
        registerRestaurantRequest.setRestaurant(restaurantProfile1);
        registerRestaurantRequest.setOwner(registerRestaurantOwnerRequest1);

        restaurantProfileFacade.registerRestaurant(registerRestaurantRequest);


        //assertThat(result).isNotNull();

    }

    @Test
    @DisplayName("Must call service to update email a restaurant successfully")
    void updateRestaurantUserEmail_ShouldCallUpdateRestaurantUserEmailUseCase() {
        restaurantProfileFacade.updateRestaurantUserEmail(userRequest, 1L);

    }

    @Test
    @DisplayName("Must call service to retrieve by name a restaurant successfully")
    void retrieveRestaurantByBusinessName_ShouldCallRetrieveRestaurantByBusinessNameUseCase() {

        var result = restaurantProfileFacade.retrieveRestaurantByBusinessName("Test Restaurant");

        assertNotNull(result);
        assertEquals(result, restaurantProfileResponse);
    }

    @Test
    @DisplayName("Must call service to retrieve by email a restaurant successfully")
    void retrieveRestaurantByEmail_ShouldCallRetrieveRestaurantByEmailUseCase() {

        var result = restaurantProfileFacade.retrieveRestaurantByEmail(userRequest.getEmail());

        assertNotNull(result);
        assertEquals(result, restaurantProfileResponse);
    }

    @Test
    @DisplayName("Must call service to update a restaurant successfully")
    void updateRestaurantProfile_ShouldCallUpdateRestaurantProfileUseCase() {

        restaurantProfileFacade.updateRestaurantProfile(restaurantProfileRequest, 1L);

    }

    @Test
    @DisplayName("Must call service to delete a restaurant successfully")
    void delete_ShouldCallDeleteRestaurantProfileUseCase() {
        restaurantProfileFacade.delete(1L);

    }

    @Test
    @DisplayName("Must call service to update restaurant owner successfully")
    void updateRestaurantOwner_ShouldCallUpdateRestaurantOwnerUseCase() {

        var registerRestaurantOwnerRequestUpdate = registerRestaurantRequest;
        Long userId = 1L;

        restaurantProfileFacade.updateRestaurantOwner(registerRestaurantOwnerRequestUpdate.getOwner(), userId);

    }

    @Test
    @DisplayName("Should retrieve restaurant by ID and convert to IsDeliveryRestaurantResponse")
    void retrieveRestaurantById_shouldRetrieveAndConvert() {

        Long restaurantId = Instancio.create(Long.class);
        IsDeliveryRestaurantResponse expectedResponse = Instancio.create(IsDeliveryRestaurantResponse.class);

        IsDeliveryRestaurantResponse actualResponse = restaurantProfileFacade.retrieveRestaurantById(restaurantId);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }
}
