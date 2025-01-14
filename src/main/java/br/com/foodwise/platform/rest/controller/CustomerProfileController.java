package br.com.foodwise.platform.rest.controller;

import br.com.foodwise.platform.model.entities.User;
import br.com.foodwise.platform.rest.dtos.request.register.customer.RegisterCustomerRequest;
import br.com.foodwise.platform.rest.dtos.response.CustomerProfileResponse;
import br.com.foodwise.platform.service.CustomerProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
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
@Tag(name = "Customer", description = "Customer crud controller")
public class CustomerProfileController {
    private final CustomerProfileService customerProfileService;

    @Operation(
            description = "Register a new Customer",
            summary = "Customer registration"
    )
    @PostMapping("/register")
    public ResponseEntity<Void> registerCustomer(@RequestBody @Valid RegisterCustomerRequest request) {
        customerProfileService.registerCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            description = "Returns the profile of the customer searched by email",
            summary = "Customer profile"
    )
    @GetMapping
    public ResponseEntity<CustomerProfileResponse> retrieveCustomerByEmail(@RequestParam @NotNull String email) {
        var response = customerProfileService.retrieveCustomerByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            description = "Returns the customer profile of the logged in user",
            summary = "Customer profile"
    )
    @GetMapping("/my-profile")
    public ResponseEntity<CustomerProfileResponse> retrieveMyProfile() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (User) authentication.getPrincipal();
        var response = customerProfileService.retrieveCustomerByEmail(user.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            description = "Delete customer profile by id",
            summary = "Customer profile"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")
                                       @NotNull
                                       long id) {
        customerProfileService.delete(id) ;
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
