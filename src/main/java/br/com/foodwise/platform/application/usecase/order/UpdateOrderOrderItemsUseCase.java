package br.com.foodwise.platform.application.usecase.order;

import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.gateway.OrderGateway;
import br.com.foodwise.platform.gateway.OrderItemGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateOrderOrderItemsUseCase {
    private final OrderGateway orderGateway;
    private final OrderItemGateway orderItemGateway;

    public void updateOrderItems(Order order, List<MenuItem> menuItemList) {
        order.updateOrderItems(menuItemList);
        orderGateway.updateOrderItems(order);
    }
}
