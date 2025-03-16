package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.domain.CustomerProfile;
import br.com.foodwise.platform.gateway.CustomerProfileGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveCustomerProfileByIdUseCase {
    private final CustomerProfileGateway customerProfileGateway;

    public CustomerProfile retrieveById(Long id) {
        return customerProfileGateway.findById(id);
    }
}
