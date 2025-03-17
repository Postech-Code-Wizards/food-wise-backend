package br.com.foodwise.platform.gateway;

import br.com.foodwise.platform.domain.OrderItem;

import java.util.List;

public interface OrderItemGateway {
    void save(OrderItem orderItem);

    List<OrderItem> saveAll(List<OrderItem> orderItemsList);

    OrderItem findOrderItemById(Long id);
}
