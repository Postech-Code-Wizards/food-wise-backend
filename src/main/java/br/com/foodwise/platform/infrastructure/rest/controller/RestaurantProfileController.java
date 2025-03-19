package br.com.foodwise.platform.infrastructure.rest.controller;

import br.com.foodwise.platform.application.facade.RestaurantProfileFacade;
import br.com.foodwise.platform.application.facade.UserFacade;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.PasswordRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RegisterRestaurantOwnerRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RegisterRestaurantRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.IsDeliveryRestaurantResponse;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/restaurant")
public class RestaurantProfileController implements RestaurantProfileApi {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantProfileController.class);
    private final RestaurantProfileFacade restaurantProfileFacade;
    private final UserFacade userFacade;

    @Override
    public ResponseEntity<Void> registerRestaurant(@RequestBody @Valid RegisterRestaurantRequest request) {
        restaurantProfileFacade.registerRestaurant(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<RestaurantProfileResponse> retrieveMyProfile(String email) {
        var restaurantProfile = restaurantProfileFacade.retrieveRestaurantByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(restaurantProfile);
    }

    @Override
    public ResponseEntity<RestaurantProfileResponse> retrieveRestaurantByBusinessName(@RequestParam
                                                                                      @NotNull
                                                                                      @NotBlank
                                                                                      String businessName) {
        var restaurantProfileResponse = restaurantProfileFacade.retrieveRestaurantByBusinessName(businessName);
        return ResponseEntity.status(HttpStatus.OK).body(restaurantProfileResponse);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("id")
                                       @NotNull
                                       long id) {
        restaurantProfileFacade.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<RestaurantProfileRequest> changeMyProfile(
            @PathVariable("id") Long id,
            @Valid @RequestBody RestaurantProfileRequest restaurantProfileRequest) {
        restaurantProfileFacade.updateRestaurantProfile(restaurantProfileRequest, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<RegisterRestaurantOwnerRequest> changeOwnerProfile(Long userId, RegisterRestaurantOwnerRequest registerRestaurantOwnerRequest) {
        restaurantProfileFacade.updateRestaurantOwner(registerRestaurantOwnerRequest, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<RestaurantProfileRequest> changeMyEmail(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserRequest userRequest
    ) {
        restaurantProfileFacade.updateRestaurantUserEmail(userRequest, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<RestaurantProfileResponse> retrieveRestaurantByEmail(@RequestParam @NotNull String email) {
        var restaurantProfileResponse = restaurantProfileFacade.retrieveRestaurantByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(restaurantProfileResponse);
    }

    @Override
    public ResponseEntity<Void> changePassword(@Valid @RequestBody PasswordRequest passwordRequest) {
        userFacade.updatePassword(passwordRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<IsDeliveryRestaurantResponse> retrieveRestaurantById(@PathVariable("id") @NotNull Long id) {
        var response = restaurantProfileFacade.retrieveRestaurantById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
