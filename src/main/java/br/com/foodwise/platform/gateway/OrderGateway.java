package br.com.foodwise.platform.gateway;

import br.com.foodwise.platform.domain.Order;

import java.util.List;

public interface OrderGateway {

    Order findById(Long id);

    boolean existsById(Long id);

    Order save(Order order);

    List<Order> retrieveAllOrders();
}
