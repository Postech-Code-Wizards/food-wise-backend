package br.com.foodwise.platform.application.usecase.order;

import br.com.foodwise.platform.domain.Address;
import br.com.foodwise.platform.gateway.CustomerProfileGateway;
import br.com.foodwise.platform.gateway.OrderGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateOrderCustomerAddressUseCase {
    private final OrderGateway orderGateway;
    private final CustomerProfileGateway customerProfileGateway;

    public void updateOrder(Long id, Address address) {
        var order = orderGateway.findById(id);
        var customerProfile = order.getCustomerProfile();

        customerProfile.updateAddress(address);
        customerProfileGateway.save(customerProfile);

        var savedCustomerProfile = customerProfileGateway.findById(customerProfile.getId());
        order.updateOrderAddressCustomer(savedCustomerProfile);
        orderGateway.save(order);
    }
}
