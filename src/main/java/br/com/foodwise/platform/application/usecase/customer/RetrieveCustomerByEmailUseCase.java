package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.domain.CustomerProfile;
import br.com.foodwise.platform.gateway.CustomerProfileGateway;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveCustomerByEmailUseCase {

    private final CustomerProfileGateway customerProfileGateway;

    public CustomerProfile execute() {

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (UserEntity) authentication.getPrincipal();

        return customerProfileGateway
                .findByUserEmail(user.getEmail());
    }

}
