package br.com.foodwise.platform.infrastructure.rest.controller;

import br.com.foodwise.platform.domain.entities.User;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.PasswordRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.CustomerProfileRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.RegisterCustomerRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.CustomerProfileResponse;
import br.com.foodwise.platform.service.CustomerProfileService;
import br.com.foodwise.platform.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
@Tag(name = "Customer", description = "Customer crud controller")
public class CustomerProfileController implements CustomerProfileApi {
    private static final Logger logger = LoggerFactory.getLogger(CustomerProfileController.class);
    private final CustomerProfileService customerProfileService;
    private final UserService userService;

    @Override
    public ResponseEntity<Void> registerCustomer(@RequestBody @Valid RegisterCustomerRequest request) {
        customerProfileService.registerCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<CustomerProfileResponse> retrieveCustomerByEmail(@RequestParam @NotNull String email) {
        var response = customerProfileService.retrieveCustomerByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<CustomerProfileResponse> retrieveMyProfile() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (User) authentication.getPrincipal();
        var response = customerProfileService.retrieveCustomerByEmail(user.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("id")
                                       @NotNull
                                       long id) {
        customerProfileService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> changeMyProfile(
            @PathVariable("id") Long id,
            @Valid @RequestBody CustomerProfileRequest customerProfileRequest
    ) {
        logger.info("PUT -> /api/VX/customer/id");
        this.customerProfileService.updateCustomerProfile(customerProfileRequest, id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Void> changeMyEmail(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserRequest userRequest
    ) {
        logger.info("PUT -> /api/VX/user/id");
        this.customerProfileService.updateCustomerUserEmail(userRequest, id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Void> changePassword(@Valid @RequestBody PasswordRequest passwordRequest) {
        logger.info("PUT -> /api/VX/user");
        this.userService.updatePassword(passwordRequest);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
