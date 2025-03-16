package br.com.foodwise.platform.application.usecase.order;

import br.com.foodwise.platform.domain.OrderItem;
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

    public void updateOrderItems(Long id, List<OrderItem> orderItems) {
        var order = orderGateway.findById(id);
        order.updateOrderItems(orderItems);

        orderItemGateway.saveAll(orderItems);
        orderGateway.save(order);
    }
}
