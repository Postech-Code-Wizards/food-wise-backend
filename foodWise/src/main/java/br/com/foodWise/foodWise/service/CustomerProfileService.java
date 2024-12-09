package br.com.foodWise.foodWise.service;

import br.com.foodWise.foodWise.model.entities.CustomerProfile;
import br.com.foodWise.foodWise.model.repositories.CustomerProfileRepository;
import br.com.foodWise.foodWise.rest.converter.CustomerProfileRequestToEntityConverter;
import br.com.foodWise.foodWise.rest.dtos.request.register.CustomerProfileRequest;
import br.com.foodWise.foodWise.rest.dtos.request.register.RegisterCustomerRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class CustomerProfileService {
    private final CustomerProfileRepository customerProfileRepository;
    private final CustomerProfileRequestToEntityConverter converter;
    private final UserService userService;

    @Transactional
    public void registerCustomer(RegisterCustomerRequest request) {
        var userRequest = request.getUser();
        userService.createUser(userRequest.getEmail(), userRequest.getPassword(), userRequest.getRole());

        var customerRequest = request.getCustomer();
        var newCustomer = this.convertToCustomerProfile(customerRequest);
        customerProfileRepository.save(newCustomer);
    }

    public CustomerProfile convertToCustomerProfile(CustomerProfileRequest customerProfileRequest) {
        var customer = converter.convert(customerProfileRequest);
        if (ObjectUtils.isEmpty(customer)) {
            throw new IllegalArgumentException("Customer profile conversion failed.");
        }
        return customer;
    }
}
