package br.com.foodWise.foodWise.auth.service;
import br.com.foodWise.foodWise.auth.rest.converter.CustomerProfileRequestToEntityConverter;

import br.com.foodWise.foodWise.auth.dtos.request.register.CustomerProfileRequest;
import br.com.foodWise.foodWise.model.repositories.CustomerProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class CustomerProfileService {
    private final CustomerProfileRepository customerProfileRepository;
    private final CustomerProfileRequestToEntityConverter converter;

    public void createCustomerProfile(CustomerProfileRequest customerProfileRequest) {
        var customer = converter.convert(customerProfileRequest);
        if (ObjectUtils.isEmpty(customer)) {
            throw new IllegalArgumentException("Customer profile conversion failed.");
        }
        customerProfileRepository.save(customer);
    }
}
