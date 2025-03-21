package br.com.foodwise.platform.infrastructure.rest.controller;

import br.com.foodwise.platform.application.facade.CustomerProfileFacade;
import br.com.foodwise.platform.application.facade.UserFacade;
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
    private final CustomerProfileFacade customerProfileFacade;
    private final UserFacade userFacade;

    @Override
    public ResponseEntity<Void> registerCustomer(@RequestBody @Valid RegisterCustomerRequest request) {
        customerProfileFacade.registerCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<CustomerProfileResponse> retrieveCustomerByEmail(@RequestParam @NotNull String email) {
        var customerProfileResponse = customerProfileFacade.retrieveCustomerByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(customerProfileResponse);
    }

    @Override
    public ResponseEntity<CustomerProfileResponse> retrieveMyProfile() {
        var customerProfileResponse = customerProfileFacade.retrieveCustomerByEmailAuthenticated();
        return ResponseEntity.status(HttpStatus.OK).body(customerProfileResponse);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("id")
                                       @NotNull
                                       long id) {
        customerProfileFacade.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Void> changeMyProfile(
            @PathVariable("id") Long id,
            @Valid @RequestBody CustomerProfileRequest customerProfileRequest) {
        this.customerProfileFacade.updateCustomerProfile(customerProfileRequest, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Void> changeMyEmail(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserRequest userRequest) {
        this.customerProfileFacade.updateCustomerUserEmail(userRequest, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Void> changePassword(@Valid @RequestBody PasswordRequest passwordRequest) {
        this.userFacade.updatePassword(passwordRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
