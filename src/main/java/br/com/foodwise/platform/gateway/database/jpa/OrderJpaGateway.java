package br.com.foodwise.platform.gateway.database.jpa;

import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.gateway.OrderGateway;
import br.com.foodwise.platform.gateway.database.jpa.converter.AddressDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.CustomerProfileDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.RestaurantProfileDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.orderitem.CreateOrderItemDomainToOrderItemEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.orderpayment.OrderPaymentDomainToOrderPaymentEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.orders.OrderDomainToOrderEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.orders.OrderEntityToOrderDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.OrderEntity;
import br.com.foodwise.platform.gateway.database.jpa.repository.OrderRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.order.OrderEmptyException;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.order.OrderItemNotFoundException;
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
    private final OrderEntityToOrderDomainConverter toOrderConverter;
    private final OrderDomainToOrderEntityConverter toOrderEntityConverter;
    private final AddressDomainToEntityConverter addressDomainToEntityConverter;
    private final CreateOrderItemDomainToOrderItemEntityConverter toOrderItemEntityConverter;
    private final CustomerProfileDomainToEntityConverter customerProfileDomainToEntityConverter;
    private final OrderPaymentDomainToOrderPaymentEntityConverter toOrderPaymentEntityConverter;
    private final RestaurantProfileDomainToEntityConverter restaurantProfileDomainToEntityConverter;

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
    @Override
    public Order createOrderSave(Order order) {
        var orderEntity = toOrderEntityConverter.convert(order);
        if (ObjectUtils.isEmpty(orderEntity)) {
            throw new OrderEmptyException("Order is empty");
        }
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

    @Transactional
    @Override
    public Order updateOrderCustomerAddress(Order order) {
        OrderEntity orderEntity = orderRepository.findById(order.getId())
                .orElseThrow(() -> new OrderItemNotFoundException("Order not found for id " + order.getId()));
        orderEntity.setCustomerProfileEntity(customerProfileDomainToEntityConverter.convert(order.getCustomerProfile()));
        orderEntity.setAddressCustomerEntity(addressDomainToEntityConverter.convert(order.getAddressCustomer()));

        var orderSaved = orderRepository.save(orderEntity);
        return toOrderConverter.convert(orderSaved);
    }

    @Transactional
    @Override
    public Order updateOrderRestaurantAddress(Order order) {
        OrderEntity orderEntity = orderRepository.findById(order.getId())
                .orElseThrow(() -> new OrderItemNotFoundException("Order not found for id " + order.getId()));
        orderEntity.setRestaurantProfileEntity(restaurantProfileDomainToEntityConverter.convert(order.getRestaurantProfile()));
        orderEntity.setAddressRestaurantEntity(addressDomainToEntityConverter.convert(order.getAddressRestaurant()));

        var orderSaved = orderRepository.save(orderEntity);
        return toOrderConverter.convert(orderSaved);
    }

    @Transactional
    @Override
    public Order updateOrderPayment(Order order) {
        OrderEntity orderEntity = orderRepository.findById(order.getId())
                .orElseThrow(() -> new OrderItemNotFoundException("Order not found for id " + order.getId()));
        orderEntity.setOrderPaymentEntity(toOrderPaymentEntityConverter.convert(order.getOrderPayment()));

        var orderSaved = orderRepository.save(orderEntity);
        return toOrderConverter.convert(orderSaved);
    }

    @Transactional
    @Override
    public Order updateTotalPrice(Order order) {
        OrderEntity orderEntity = orderRepository.findById(order.getId())
                .orElseThrow(() -> new OrderItemNotFoundException("Order not found for id " + order.getId()));
        orderEntity.setTotalPrice(order.getTotalPrice());

        var orderSaved = orderRepository.save(orderEntity);
        return toOrderConverter.convert(orderSaved);
    }

    @Transactional
    @Override
    public Order updateOrderItems(Order order) {
        OrderEntity orderEntity = orderRepository.findById(order.getId())
                .orElseThrow(() -> new OrderItemNotFoundException("Order not found for id " + order.getId()));
        orderEntity.setOrderItemsEntity(toOrderItemEntityConverter.convert(order.getOrderItems()));

        var orderSaved = orderRepository.save(orderEntity);
        return toOrderConverter.convert(orderSaved);
    }

    @Override
    public Order cancelOrderSave(Order order) {
        var orderEntity = toOrderEntityConverter.convert(order);
        var savedOrder = orderRepository.save(orderEntity);
        return toOrderConverter.convert(savedOrder);
    }
}
