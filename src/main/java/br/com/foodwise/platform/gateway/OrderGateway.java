package br.com.foodwise.platform.gateway;

import br.com.foodwise.platform.domain.Order;
import jakarta.transaction.Transactional;

import java.util.List;

public interface OrderGateway {

    Order findById(Long id);

    boolean existsById(Long id);

    Order createOrderSave(Order order);

    Order cancelOrderSave(Order order);

    Order updateOrderCustomerAddress(Order order);

    List<Order> retrieveAllOrders();

    @Transactional
    Order updateOrderRestaurantAddress(Order order);

    @Transactional
    Order updateOrderPayment(Order order);

    @Transactional
    Order updateTotalPrice(Order order);

    @Transactional
    Order updateOrderItems(Order order);
}
