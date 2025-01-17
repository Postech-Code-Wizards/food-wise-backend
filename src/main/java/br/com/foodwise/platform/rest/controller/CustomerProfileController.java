package br.com.foodwise.platform.rest.controller;

import br.com.foodwise.platform.model.entities.User;
import br.com.foodwise.platform.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.rest.dtos.request.register.customer.CustomerProfileRequest;
import br.com.foodwise.platform.rest.dtos.request.register.customer.RegisterCustomerRequest;
import br.com.foodwise.platform.rest.dtos.response.CustomerProfileResponse;
import br.com.foodwise.platform.service.CustomerProfileService;
import br.com.foodwise.platform.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@RequestMapping("api/v1/customer")
@Tag(name = "Customer", description = "Customer crud controller")
public class CustomerProfileController {
    private final CustomerProfileService customerProfileService;
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerProfileController.class);


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
    public ResponseEntity<Void> registerCustomer(@RequestBody @Valid RegisterCustomerRequest request) {
        customerProfileService.registerCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Validate if the customer profile exists by email",
            description = "Validate if the customer profile exists by email")
    @ApiResponse(
            responseCode = "200", description = "Success request"
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
                                                "code": "error-2",
                                                "message": "Not found"
                                            }
                                        ]
                                    }
                                    """
                    )
            )
    )

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
    public ResponseEntity<CustomerProfileResponse> retrieveCustomerByEmail(@RequestParam @NotNull String email) {
        var response = customerProfileService.retrieveCustomerByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/my-profile")
    public ResponseEntity<CustomerProfileResponse> retrieveMyProfile() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (User) authentication.getPrincipal();
        var response = customerProfileService.retrieveCustomerByEmail(user.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            description = "Delete customer profile by id",
            summary = "Customer profile",
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
        customerProfileService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

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
    public ResponseEntity<Void> changeMyProfile(
            @PathVariable("id") Long id,
            @Valid @RequestBody CustomerProfileRequest customerProfileRequest
    ) {
        logger.info("PUT -> /api/VX/customer/id");
        this.customerProfileService.updateCustomerProfile(customerProfileRequest, id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

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
    public ResponseEntity<Void> changeMyEmail(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserRequest userRequest
    ) {
        logger.info("PUT -> /api/VX/user/id");
        this.userService.updateUserEmail(userRequest, id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
