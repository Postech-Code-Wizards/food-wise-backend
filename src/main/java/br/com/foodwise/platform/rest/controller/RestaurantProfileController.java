package br.com.foodwise.platform.rest.controller;

import br.com.foodwise.platform.model.entities.User;
import br.com.foodwise.platform.rest.dtos.request.register.restaurant.RegisterRestaurantRequest;
import br.com.foodwise.platform.rest.dtos.response.RestaurantProfileResponse;
import br.com.foodwise.platform.service.RestaurantProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/restaurant")
@Tag(name = "Restaurant", description = "Restaurant crud controller")
public class RestaurantProfileController {

    private final RestaurantProfileService restaurantProfileService;

    @Operation(
            description = "Register a new restaurant",
            summary = "Restaurant registration",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201"),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Email already exists",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            """
                                            {
                                                "statusCode": 409,
                                                "errors": [
                                                    {
                                                        "code": "EMAIL_ALREADY_EXISTS",
                                                        "message": "Email already exists"
                                                    }
                                                ]
                                            }
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Sorry, internal server error, try again later",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            """
                                            {
                                                "statusCode": 500,
                                                "errors": [
                                                    {
                                                        "code": "INTERNAL_SERVER_ERROR",
                                                        "message": "Sorry, internal server error, try again later"
                                                    }
                                                ]
                                            }
                                            """
                                    )
                            )
                    )

            }
    )
    @PostMapping("/register")
    public ResponseEntity<Void> registerRestaurant(@RequestBody @Valid RegisterRestaurantRequest request) {
        restaurantProfileService.registerRestaurant(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            description = "Returns the restaurant profile of the logged in user",
            summary = "Restaurant profile",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RestaurantProfileResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found restaurant profile",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            """
                                            {
                                                "statusCode": 404,
                                                "errors": [
                                                    {
                                                        "code": "NOT_FOUND",
                                                        "message": "Email user_mail not found"
                                                    }
                                                ]
                                            }
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Sorry, internal server error, try again later",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            """
                                            {
                                                "statusCode": 500,
                                                "errors": [
                                                    {
                                                        "code": "INTERNAL_SERVER_ERROR",
                                                        "message": "Sorry, internal server error, try again later"
                                                    }
                                                ]
                                            }
                                            """
                                    )
                            )
                    )

            }
    )
    @GetMapping("/my-profile")
    public ResponseEntity<RestaurantProfileResponse> retrieveMyProfile() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (User) authentication.getPrincipal();
        var response = restaurantProfileService.retrieveRestaurantByEmail(user.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            description = "Returns the profile of the restaurant searched by name",
            summary = "Restaurant profile",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RestaurantProfileResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found restaurant profile",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            """
                                            {
                                                "statusCode": 404,
                                                "errors": [
                                                    {
                                                        "code": "NOT_FOUND",
                                                        "message": "Restaurant businessName not found"
                                                    }
                                                ]
                                            }
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Sorry, internal server error, try again later",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            """
                                            {
                                                "statusCode": 500,
                                                "errors": [
                                                    {
                                                        "code": "INTERNAL_SERVER_ERROR",
                                                        "message": "Sorry, internal server error, try again later"
                                                    }
                                                ]
                                            }
                                            """
                                    )
                            )
                    )

            }
    )
    @GetMapping
    public ResponseEntity<RestaurantProfileResponse> retrieveRestaurantByBusinessName(@RequestParam
                                                                                      @NotNull
                                                                                      @NotBlank
                                                                                      String businessName) {
        var response = restaurantProfileService.retrieveRestaurantByBusinessName(businessName);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            description = "Delete restaurant profile by id",
            summary = "Restaurant profile",
            responses = {
                    @ApiResponse(responseCode = "200",description = "Ok"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User does not exist",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            """
                                            {
                                                "statusCode": 404,
                                                "errors": [
                                                    {
                                                        "code": "USER_DOES_NOT_EXIST",
                                                        "message": "User does not exist"
                                                    }
                                                ]
                                            }
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Sorry, internal server error, try again later",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            """
                                            {
                                                "statusCode": 500,
                                                "errors": [
                                                    {
                                                        "code": "INTERNAL_SERVER_ERROR",
                                                        "message": "Sorry, internal server error, try again later"
                                                    }
                                                ]
                                            }
                                            """
                                    )
                            )
                    )

            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")
                                       @NotNull
                                       long id) {
        restaurantProfileService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
