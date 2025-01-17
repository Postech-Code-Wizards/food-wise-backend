package br.com.foodwise.platform.rest.controller;

import br.com.foodwise.platform.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.rest.dtos.request.register.restaurant.RegisterRestaurantRequest;
import br.com.foodwise.platform.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import br.com.foodwise.platform.rest.dtos.response.RestaurantProfileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface RestaurantProfileApi {

    @Operation(
            description = "Register a new Restaurant",
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
    public ResponseEntity<Void> registerRestaurant(@RequestBody @Valid RegisterRestaurantRequest request);

    @Operation(
            description = "Returns the profile of the restaurant searched by token",
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
                                                        "message": "not found"
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
    public ResponseEntity<RestaurantProfileResponse> retrieveMyProfile();

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
                                                                                      String businessName);

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
                                       long id);

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
    );

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
    );

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
    public ResponseEntity<RestaurantProfileResponse> retrieveRestaurantByEmail(@RequestParam @NotNull String email);
}
