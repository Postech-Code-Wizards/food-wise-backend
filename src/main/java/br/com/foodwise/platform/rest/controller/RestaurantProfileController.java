package br.com.foodwise.platform.rest.controller;

import br.com.foodwise.platform.model.entities.User;
import br.com.foodwise.platform.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.rest.dtos.request.register.restaurant.RegisterRestaurantRequest;
import br.com.foodwise.platform.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import br.com.foodwise.platform.rest.dtos.response.RestaurantProfileResponse;
import br.com.foodwise.platform.service.RestaurantProfileService;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")
                                       @NotNull
                                       long id) {
        restaurantProfileService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Updates Restaurant profile data", description = "Update restaurant profile data, such as " +
            "businessName, description, businessHours, deliveryRadius, cuisineType, address and phone")
    @ApiResponse(
            responseCode = "204", description = "NO CONTENT, no data to return"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not Found when customer id is wrong",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            """
                                    {
                                        "statusCode": 404,
                                        "errors": [
                                            {
                                                "code": "RESTAURANT_DOES_NOT_EXIST",
                                                "message": "Restaurante não existe"
                                            }
                                        ]
                                    }
                                    """
                    )
            )
    )
    @PutMapping("/{id}/profile")
    public ResponseEntity<RestaurantProfileRequest> changeMyProfile(
            @PathVariable("id") Long id,
            @Valid @RequestBody RestaurantProfileRequest restaurantProfileRequest
    ) {
        logger.info("PUT -> /api/VX/restaurant/id");
        restaurantProfileService.updateRestaurantProfile(restaurantProfileRequest, id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Updates Restaurant USER E-mail", description = "Update restaurant USER email")
    @ApiResponse(
            responseCode = "204", description = "NO CONTENT, no data to return"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not Found when customer id is wrong",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            """
                                    {
                                        "statusCode": 404,
                                        "errors": [
                                            {
                                                "code": "USER_DOES_NOT_EXIST",
                                                "message": "Usuário não existe"
                                            }
                                        ]
                                    }
                                    """
                    )
            )
    )
    @PutMapping("/{id}/updateEmail")
    public ResponseEntity<RestaurantProfileRequest> changeMyEmail(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserRequest userRequest
    ) {
        logger.info("PUT -> /api/VX/user/id");
        userService.updateUserEmail(userRequest, id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Validate if the restaurant profile exists by email",
            description = "Validate if the restaurant profile exists by email")
    @ApiResponse(
            responseCode = "200", description = "Success request"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not Found when restaurant id is wrong",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            """
                                    {
                                        "statusCode": 404,
                                        "errors": [
                                            {
                                                "code": "error-2",
                                                "message": "Not found"
                                            }
                                        ]
                                    }
                                    """
                    )
            )
    )
    @GetMapping("/retrieve-login")
    public ResponseEntity<RestaurantProfileResponse> retrieveRestaurantByEmail(@RequestParam @NotNull String email) {
        var response = restaurantProfileService.retrieveRestaurantByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
