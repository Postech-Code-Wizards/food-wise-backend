package br.com.foodWise.foodWise.rest.controller;

import br.com.foodWise.foodWise.rest.dtos.request.register.RegisterCustomerRequest;
import br.com.foodWise.foodWise.service.CustomerProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomerProfileController {
    private final CustomerProfileService customerProfileService;

    @PostMapping
    public ResponseEntity<Void> registerCustomer(@RequestBody @Valid RegisterCustomerRequest request) {
        customerProfileService.registerCustomer(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
