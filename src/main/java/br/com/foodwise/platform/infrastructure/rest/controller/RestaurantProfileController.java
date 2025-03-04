package br.com.foodwise.platform.infrastructure.rest.controller;

import br.com.foodwise.platform.application.service.RestaurantProfileService;
import br.com.foodwise.platform.application.service.UserService;
import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.infrastructure.rest.converter.common.PasswordRequestToDomainConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.common.UserRequestToDomainConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.restaurant.RestaurantProfileDomainToResponseConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.restaurant.RestaurantProfileRequestToDomainConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.PasswordRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RegisterRestaurantRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/restaurant")
public class RestaurantProfileController implements RestaurantProfileApi {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantProfileController.class);
    private final RestaurantProfileService restaurantProfileService;
    private final UserService userService;
    private final RestaurantProfileRequestToDomainConverter restaurantProfileRequestToDomainConverter;
    private final RestaurantProfileDomainToResponseConverter restaurantProfileDomainToResponseConverter;
    private final UserRequestToDomainConverter userRequestToDomainConverter;
    private final PasswordRequestToDomainConverter passwordRequestToDomainConverter;

    @Override
    public ResponseEntity<Void> registerRestaurant(@RequestBody @Valid RegisterRestaurantRequest request) {

        User user = userRequestToDomainConverter.convert(request.getUser());
        RestaurantProfile restaurantProfile = restaurantProfileRequestToDomainConverter.convert(request.getRestaurant(), user);
        restaurantProfileService.registerRestaurant(restaurantProfile);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<RestaurantProfileResponse> retrieveMyProfile() {
        var restaurantProfile = restaurantProfileService.retrieveRestaurantByEmail();
        var response = restaurantProfileDomainToResponseConverter.convert(restaurantProfile);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<RestaurantProfileResponse> retrieveRestaurantByBusinessName(@RequestParam
                                                                                      @NotNull
                                                                                      @NotBlank
                                                                                      String businessName) {
        var restaurantProfile = restaurantProfileService.retrieveRestaurantByBusinessName(businessName);
        var response = restaurantProfileDomainToResponseConverter.convert(restaurantProfile);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("id")
                                       @NotNull
                                       long id) {
        restaurantProfileService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<RestaurantProfileRequest> changeMyProfile(
            @PathVariable("id") Long id,
            @Valid @RequestBody RestaurantProfileRequest restaurantProfileRequest
    ) {
        RestaurantProfile restaurantProfile = restaurantProfileRequestToDomainConverter.convert(restaurantProfileRequest);
        restaurantProfileService.updateRestaurantProfile(restaurantProfile, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<RestaurantProfileRequest> changeMyEmail(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserRequest userRequest
    ) {
        var user = userRequestToDomainConverter.convert(userRequest);
        restaurantProfileService.updateRestaurantUserEmail(user, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<RestaurantProfileResponse> retrieveRestaurantByEmail(@RequestParam @NotNull String email) {
        var restaurantProfile = restaurantProfileService.retrieveRestaurantByEmail();
        var response = restaurantProfileDomainToResponseConverter.convert(restaurantProfile);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Void> changePassword(@Valid @RequestBody PasswordRequest passwordRequest) {
        User user = passwordRequestToDomainConverter.convert(passwordRequest);
        this.userService.updatePassword(user, passwordRequest.getNewPassword());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
