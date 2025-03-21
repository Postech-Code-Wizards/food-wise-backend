package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.domain.CustomerProfile;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.gateway.CustomerProfileGateway;
import br.com.foodwise.platform.gateway.UserGateway;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.BusinessException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterCustomerUseCase {

    private final CustomerProfileGateway customerProfileGateway;
    private final UserGateway userGateway;

    @Transactional
    public void execute(CustomerProfile customerProfile) {
        if (userGateway.existsByEmail(customerProfile.getUser().getEmail())) {
            throw new BusinessException("EMAIL_ALREADY_EXISTS", HttpStatus.CONFLICT, "");
        }

        customerProfile.getUser().registerUser(UserType.CUSTOMER);
        customerProfileGateway.save(customerProfile);
    }

}
