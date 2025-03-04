package br.com.foodwise.platform.infrastructure.rest.controller;

import br.com.foodwise.platform.application.service.CustomerProfileService;
import br.com.foodwise.platform.application.service.UserService;
import br.com.foodwise.platform.domain.CustomerProfile;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.infrastructure.rest.converter.common.PasswordRequestToDomainConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.common.UserRequestToDomainConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.customer.CustomerProfileDomainToResponseConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.customer.CustomerProfileRequestToDomainConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.PasswordRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.CustomerProfileRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.RegisterCustomerRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.CustomerProfileResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
@Tag(name = "Customer", description = "Customer crud controller")
public class CustomerProfileController implements CustomerProfileApi {

    private static final Logger logger = LoggerFactory.getLogger(CustomerProfileController.class);
    private final CustomerProfileService customerProfileService;
    private final UserService userService;
    private final CustomerProfileRequestToDomainConverter customerProfileRequestToDomainConverter;
    private final CustomerProfileDomainToResponseConverter customerProfileDomainToResponseConverter;
    private final UserRequestToDomainConverter userRequestToDomainConverter;
    private final PasswordRequestToDomainConverter passwordRequestToDomainConverter;

    @Override
    public ResponseEntity<Void> registerCustomer(@RequestBody @Valid RegisterCustomerRequest request) {
        User user = userRequestToDomainConverter.convert(request.getUser());
        CustomerProfile customerProfile = customerProfileRequestToDomainConverter.convert(request.getCustomer(), user);
        customerProfileService.registerCustomer(customerProfile);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<CustomerProfileResponse> retrieveCustomerByEmail(@RequestParam @NotNull String email) {
        var customerProfile = customerProfileService.retrieveCustomerByEmail();
        var response = customerProfileDomainToResponseConverter.convert(customerProfile);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<CustomerProfileResponse> retrieveMyProfile() {
        var customerProfile = customerProfileService.retrieveCustomerByEmail();
        var response = customerProfileDomainToResponseConverter.convert(customerProfile);
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
            @Valid @RequestBody CustomerProfileRequest customerProfileRequest) {
        CustomerProfile customerProfile = customerProfileRequestToDomainConverter.convert(customerProfileRequest);
        this.customerProfileService.updateCustomerProfile(customerProfile, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Void> changeMyEmail(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserRequest userRequest) {
        var user = userRequestToDomainConverter.convert(userRequest);
        this.customerProfileService.updateCustomerUserEmail(user, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Void> changePassword(@Valid @RequestBody PasswordRequest passwordRequest) {
        User user = passwordRequestToDomainConverter.convert(passwordRequest);
        this.userService.updatePassword(user, passwordRequest.getNewPassword());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
