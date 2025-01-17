package br.com.foodwise.platform.rest.controller;

import br.com.foodwise.platform.model.entities.User;
import br.com.foodwise.platform.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.rest.dtos.request.register.customer.CustomerProfileRequest;
import br.com.foodwise.platform.rest.dtos.request.register.customer.RegisterCustomerRequest;
import br.com.foodwise.platform.rest.dtos.response.CustomerProfileResponse;
import br.com.foodwise.platform.service.CustomerProfileService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomerProfileController {
    private final CustomerProfileService customerProfileService;
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerProfileController.class);


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
