package br.com.foodwise.platform.service;

import br.com.foodwise.platform.model.entities.CustomerProfile;
import br.com.foodwise.platform.model.entities.enums.UserType;
import br.com.foodwise.platform.model.repositories.CustomerProfileRepository;
import br.com.foodwise.platform.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.platform.rest.converter.customer.CustomerProfileEntityToResponseConverter;
import br.com.foodwise.platform.rest.converter.customer.CustomerProfileRequestToEntityConverter;
import br.com.foodwise.platform.rest.dtos.request.register.customer.CustomerProfileRequest;
import br.com.foodwise.platform.rest.dtos.request.register.customer.RegisterCustomerRequest;
import br.com.foodwise.platform.rest.dtos.response.CustomerProfileResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class CustomerProfileService {
    private final CustomerProfileRepository customerProfileRepository;
    private final CustomerProfileRequestToEntityConverter customerProfileRequestToEntityConverter;
    private final CustomerProfileEntityToResponseConverter customerProfileEntityToResponseConverter;
    private final UserService userService;

    @Transactional
    public void registerCustomer(RegisterCustomerRequest request) {
        var userRequest = request.getUser();
        var user = userService.createUser(userRequest.getEmail(), userRequest.getPassword(), UserType.CUSTOMER);

        var customerRequest = request.getCustomer();
        var newCustomer = this.convertToCustomerProfileEntity(customerRequest);
        newCustomer.setUser(user);
        customerProfileRepository.save(newCustomer);
    }

    @Transactional
    public void updateCustomerProfile(CustomerProfileRequest customerProfileRequest, Long id) {
        var existingCustomer = customerProfileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CUSTOMER_DOES_NOT_EXIST"));

        var customerProfile = convertToCustomerProfileEntity(customerProfileRequest);

        existingCustomer.setFirstName(customerProfile.getFirstName());
        existingCustomer.setLastName(customerProfile.getLastName());
        existingCustomer.setAddress(customerProfile.getAddress());
        existingCustomer.setUpdatedAt(ZonedDateTime.now());
        existingCustomer.setPhone(customerProfile.getPhone());

        customerProfileRepository.save(existingCustomer);
    }

    public CustomerProfileResponse retrieveCustomerByEmail(@RequestParam String email) {
        var customerProfile = customerProfileRepository
                .findByUserEmail(email).orElseThrow(() -> new ResourceNotFoundException("Usuário " + email));
        return convertToCustomerProfileResponse(customerProfile);
    }

    private CustomerProfileResponse convertToCustomerProfileResponse(CustomerProfile customerProfile) {
        return customerProfileEntityToResponseConverter
                .convert(customerProfile);
    }

    public CustomerProfile convertToCustomerProfileEntity(CustomerProfileRequest customerProfileRequest) {
        var customer = customerProfileRequestToEntityConverter
                .convert(customerProfileRequest);
        if (ObjectUtils.isEmpty(customer)) {
            throw new IllegalArgumentException("Customer profile conversion failed.");
        }
        return customer;
    }
}
