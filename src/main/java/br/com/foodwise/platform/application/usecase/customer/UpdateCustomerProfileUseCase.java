package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.domain.CustomerProfile;
import br.com.foodwise.platform.gateway.CustomerProfileGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class UpdateCustomerProfileUseCase {

    private final CustomerProfileGateway customerProfileGateway;

    private static CustomerProfile populate(CustomerProfile customerProfile, CustomerProfile existingCustomerProfile) {
        return new CustomerProfile(
                existingCustomerProfile.getId(),
                customerProfile.getFirstName(),
                customerProfile.getLastName(),
                customerProfile.getAddress(),
                existingCustomerProfile.getCreatedAt(),
                ZonedDateTime.now(),
                existingCustomerProfile.getUser(),
                customerProfile.getPhone()
        );
    }

    @Transactional
    public void execute(CustomerProfile customerProfile, Long id) {
        var existingCustomerProfile = customerProfileGateway.findById(id);

        var customerProfileUpdate = populate(customerProfile, existingCustomerProfile);
        customerProfileGateway.save(customerProfileUpdate);
    }

}
