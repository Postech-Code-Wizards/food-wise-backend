package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.domain.entities.CustomerProfile;
import br.com.foodwise.platform.infrastructure.rest.converter.customer.CustomerProfileEntityToResponseConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.CustomerProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConvertToCustomerProfileResponseUseCase {

    private final CustomerProfileEntityToResponseConverter customerProfileEntityToResponseConverter;

    public CustomerProfileResponse execute(CustomerProfile customerProfile) {
        return customerProfileEntityToResponseConverter
                .convert(customerProfile);
    }

}
