package br.com.foodwise.platform.gateway;

import br.com.foodwise.platform.domain.OrderStatus;

public interface OrderStatusGateway {

    OrderStatus save(OrderStatus orderStatus);

    OrderStatus findOrderStatusById(Long id);
}
