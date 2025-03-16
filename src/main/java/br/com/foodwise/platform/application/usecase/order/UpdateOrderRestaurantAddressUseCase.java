package br.com.foodwise.platform.application.usecase.order;

import br.com.foodwise.platform.domain.Address;
import br.com.foodwise.platform.gateway.OrderGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateOrderRestaurantAddressUseCase {
    private final OrderGateway orderGateway;

    public void updateOrder(Long id, Address address) {
        var order = orderGateway.findById(id);
        order.updateOrderAddressRestaurant(address);
        orderGateway.save(order);
    }
}
