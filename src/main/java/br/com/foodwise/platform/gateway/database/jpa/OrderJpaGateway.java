package br.com.foodwise.platform.gateway.database.jpa;

import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.gateway.OrderGateway;
import br.com.foodwise.platform.gateway.database.jpa.converter.orderitem.CreateOrderItemDomainToOrderItemEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.orders.OrderDomainToOrderEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.orders.OrderEntityToOrderDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.OrderEntity;
import br.com.foodwise.platform.gateway.database.jpa.repository.OrderItemRepository;
import br.com.foodwise.platform.gateway.database.jpa.repository.OrderRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.order.OrderEmptyException;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.order.OrderNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderJpaGateway implements OrderGateway {
    private final OrderRepository orderRepository;
    private final OrderDomainToOrderEntityConverter toOrderEntityConverter;
    private final OrderEntityToOrderDomainConverter toOrderConverter;
    private final CreateOrderItemDomainToOrderItemEntityConverter toOrderItemEntityConverter;
    private final OrderItemRepository orderItemRepository;

    @Override
    public Order findById(Long id) {
        return toOrderConverter.convert(orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found by id " + id)));
    }

    @Override
    public boolean existsById(Long id) {
        return orderRepository.existsById(id);
    }

    @Transactional
    public Order save(Order order) {
        var orderEntity = toOrderEntityConverter.convert(order);
        if (ObjectUtils.isEmpty(orderEntity)) {
            throw new OrderEmptyException("Order is empty");
        }
        orderRepository.save(orderEntity);

        var orderSaved = orderRepository.save(orderEntity);
        return toOrderConverter.convert(orderSaved);
    }

    @Override
    public List<Order> retrieveAllOrders() {
        var orderEntities = orderRepository.findAll();
        List<Order> orders = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntities) {
            orders.add(toOrderConverter.convert(orderEntity));
        }

        return orders;
    }
}
