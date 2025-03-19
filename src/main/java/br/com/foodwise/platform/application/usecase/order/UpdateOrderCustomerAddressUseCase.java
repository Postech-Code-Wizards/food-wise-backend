package br.com.foodwise.platform.application.usecase.order;

import br.com.foodwise.platform.application.usecase.customer.UpdateCustomerAddressUseCase;
import br.com.foodwise.platform.application.usecase.customer.UpdateCustomerProfileUseCase;
import br.com.foodwise.platform.domain.Address;
import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.gateway.OrderGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateOrderCustomerAddressUseCase {
    private final OrderGateway orderGateway;
    private final UpdateCustomerAddressUseCase updateCustomerAddressUseCase;
    private final UpdateCustomerProfileUseCase updateCustomerProfileUseCase;

    public void updateOrder(Order order, Address address) {
        var customerId = order.getCustomerProfile().getId();
        var addressUpdated = updateCustomerAddressUseCase.updateAddress(customerId, address);
        var customerProfile = order.getCustomerProfile().updateAddress(addressUpdated);
        updateCustomerProfileUseCase.execute(customerProfile, customerId);

        order.updateOrderAddressCustomer(addressUpdated);
        orderGateway.updateOrderCustomerAddress(order);
    }
}
