package br.com.foodwise.platform.application.usecase.order.orderitems;

import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.domain.OrderItem;
import br.com.foodwise.platform.gateway.OrderItemGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateOrderItemsUseCase {
    private final OrderItemGateway orderItemGateway;

    public List<OrderItem> create(List<MenuItem> menuItems, Order order) {
        List<OrderItem> orderItems = menuItems.stream().map(menuItem ->
                new OrderItem(
                        null,
                        menuItem,
                        null,
                        null,
                        order
                )
        ).toList();

        return orderItemGateway.saveAll(orderItems);
    }


}
