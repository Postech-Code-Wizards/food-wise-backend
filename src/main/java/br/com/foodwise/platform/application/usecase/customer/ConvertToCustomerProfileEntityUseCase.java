package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.domain.entities.CustomerProfile;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.platform.infrastructure.rest.converter.customer.CustomerProfileRequestToEntityConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.CustomerProfileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class ConvertToCustomerProfileEntityUseCase {

    private final CustomerProfileRequestToEntityConverter customerProfileRequestToEntityConverter;

    public CustomerProfile execute(CustomerProfileRequest customerProfileRequest) {
        var customer = customerProfileRequestToEntityConverter
                .convert(customerProfileRequest);
        if (ObjectUtils.isEmpty(customer)) {
            throw new ResourceNotFoundException("CUSTOMER_PROFILE_EXCEPTION");
        }
        return customer;
    }

}
