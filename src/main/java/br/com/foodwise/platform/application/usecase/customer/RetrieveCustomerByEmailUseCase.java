package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.domain.CustomerProfile;
import br.com.foodwise.platform.gateway.CustomerProfileGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveCustomerByEmailUseCase {

    private final CustomerProfileGateway customerProfileGateway;

    public CustomerProfile execute(String email) {
        return customerProfileGateway
                .findByUserEmail(email);
    }

}
