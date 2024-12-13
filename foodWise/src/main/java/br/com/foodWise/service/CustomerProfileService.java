package br.com.foodWise.service;

import br.com.foodWise.model.entities.CustomerProfile;
import br.com.foodWise.model.entities.enums.UserType;
import br.com.foodWise.model.repositories.CustomerProfileRepository;
import br.com.foodWise.rest.converter.customer.CustomerProfileEntityToResponseConverter;
import br.com.foodWise.rest.converter.customer.CustomerProfileRequestToEntityConverter;
import br.com.foodWise.rest.dtos.request.register.customer.CustomerProfileRequest;
import br.com.foodWise.rest.dtos.request.register.customer.RegisterCustomerRequest;
import br.com.foodWise.rest.dtos.response.CustomerProfileResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestParam;

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

    public CustomerProfileResponse retrieveCustomerByEmail(@RequestParam String email) {
        var customerProfile = customerProfileRepository
                .findByUserEmail(email).orElseThrow(IllegalArgumentException::new);
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
