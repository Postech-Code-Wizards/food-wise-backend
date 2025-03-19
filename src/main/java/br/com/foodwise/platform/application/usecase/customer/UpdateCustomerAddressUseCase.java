package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.domain.Address;
import br.com.foodwise.platform.gateway.CustomerProfileGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCustomerAddressUseCase {
    private final CustomerProfileGateway customerProfileGateway;

    public Address updateAddress(Long customerId, Address address) {
        var customerProfile = customerProfileGateway.findById(customerId);
        customerProfileGateway.save(customerProfile);
        return customerProfile.getAddress().update(address);
    }
}
