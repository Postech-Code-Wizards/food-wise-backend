package br.com.foodwise.platform.infrastructure.rest.controller;

import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.PasswordRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.CustomerProfileRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.RegisterCustomerRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.CustomerProfileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface CustomerProfileApi {

    @Operation(
            description = "Register a new Customer",
            summary = "Customer registration",
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
    ResponseEntity<Void> registerCustomer(@RequestBody @Valid RegisterCustomerRequest request);

    @Operation(
            description = "Returns the profile of the customer searched by email",
            summary = "Customer profile",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomerProfileResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found customer profile",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            """
                                                    {
                                                        "statusCode": 404,
                                                        "errors": [
                                                            {
                                                                "code": "NOT_FOUND",
                                                                "message": "Customer mail not found"
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
    @GetMapping("/retrieve-login")
    ResponseEntity<CustomerProfileResponse> retrieveCustomerByEmail(@RequestParam @NotNull String email);

    @Operation(
            description = "Returns the profile of the customer searched by token",
            summary = "Customer profile",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomerProfileResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found customer profile",
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
    ResponseEntity<CustomerProfileResponse> retrieveMyProfile();

    @Operation(
            description = "Delete customer profile by id",
            summary = "Customer profile",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok"),
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
    ResponseEntity<Void> delete(@PathVariable("id") @NotNull long id);

    @Operation(summary = "Updates Customer profile data", description = "Update customer profile data, such as" +
            "firstName, lastName, address and phone ")
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
                                                "code": "CUSTOMER_DOES_NOT_EXIST",
                                                "message": "Cliente não existe"
                                            }
                                        ]
                                    }
                                    """
                    )
            )
    )
    @PutMapping("/{id}/profile")
    ResponseEntity<Void> changeMyProfile(
            @PathVariable("id") Long id,
            @Valid @RequestBody CustomerProfileRequest customerProfileRequest
    );

    @Operation(summary = "Updates Customer USER E-mail", description = "Update customer USER email")
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
    ResponseEntity<Void> changeMyEmail(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserRequest userRequest
    );

    @Operation(summary = "Updates customer password", description = "Update customer password")
    @ApiResponse(
            responseCode = "204", description = "No content, no data to return"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Incorrect password for customer",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            """
                                    {
                                        "statusCode": 400,
                                        "errors": [
                                            {
                                                "code": "INCORRECT_PASSWORD",
                                                "message": "Password is incorrect! Try again"
                                            }
                                        ]
                                    }
                                    """
                    )
            )
    )
    @PutMapping("/updatePassword")
    ResponseEntity<Void> changePassword(@Valid @RequestBody PasswordRequest passwordRequest);
}
