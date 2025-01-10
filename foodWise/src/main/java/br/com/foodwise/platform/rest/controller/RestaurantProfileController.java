package br.com.foodwise.platform.rest.controller;

import br.com.foodwise.platform.model.entities.User;
import br.com.foodwise.platform.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.rest.dtos.request.register.restaurant.RegisterRestaurantRequest;
import br.com.foodwise.platform.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import br.com.foodwise.platform.rest.dtos.response.RestaurantProfileResponse;
import br.com.foodwise.platform.service.RestaurantProfileService;
import br.com.foodwise.platform.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/restaurant")
public class RestaurantProfileController {

    private final RestaurantProfileService restaurantProfileService;
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(RestaurantProfileController.class);

    @PostMapping("/register")
    public ResponseEntity<Void> registerRestaurant(@RequestBody @Valid RegisterRestaurantRequest request) {
        restaurantProfileService.registerRestaurant(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/my-profile")
    public ResponseEntity<RestaurantProfileResponse> retrieveMyProfile() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (User) authentication.getPrincipal();
        var response = restaurantProfileService.retrieveRestaurantByEmail(user.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<RestaurantProfileResponse> retrieveRestaurantByBusinessName(@RequestParam
                                                                                      @NotNull
                                                                                      @NotBlank
                                                                                      String businessName) {
        var response = restaurantProfileService.retrieveRestaurantByBusinessName(businessName);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}/profile")
    public ResponseEntity<RestaurantProfileRequest> changeMyProfile(
            @PathVariable("id") Long id,
            @Valid @RequestBody RestaurantProfileRequest restaurantProfileRequest
    ) {
        logger.info("PUT -> /api/VX/restaurant/id");
        restaurantProfileService.updateRestaurantProfile(restaurantProfileRequest, id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}/credentials")
    public ResponseEntity<RestaurantProfileRequest> changeMyCredentials(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserRequest userRequest
    ) {
        logger.info("PUT -> /api/VX/user/id");
        userService.updateUser(userRequest, id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
