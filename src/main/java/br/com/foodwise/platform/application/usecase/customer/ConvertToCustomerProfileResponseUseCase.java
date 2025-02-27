package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.gateway.entities.CustomerProfileEntity;
import br.com.foodwise.platform.infrastructure.rest.converter.customer.CustomerProfileEntityToResponseConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.CustomerProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConvertToCustomerProfileResponseUseCase {

    private final CustomerProfileEntityToResponseConverter customerProfileEntityToResponseConverter;

    public CustomerProfileResponse execute(CustomerProfileEntity customerProfileEntity) {
        return customerProfileEntityToResponseConverter
                .convert(customerProfileEntity);
    }

}
