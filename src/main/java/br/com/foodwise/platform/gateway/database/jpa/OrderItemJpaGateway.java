package br.com.foodwise.platform.gateway.database.jpa;


import br.com.foodwise.platform.domain.OrderItem;
import br.com.foodwise.platform.gateway.OrderItemGateway;
import br.com.foodwise.platform.gateway.database.jpa.converter.orderitem.CreateOrderItemDomainToOrderItemEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.orderitem.OrderItemEntityToOrderItemDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.orders.OrderDomainToOrderEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.OrderItemEntity;
import br.com.foodwise.platform.gateway.database.jpa.repository.OrderItemRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.order.OrderItemEmptyException;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.order.OrderItemNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderItemJpaGateway implements OrderItemGateway {
    private final OrderItemRepository orderItemRepository;
    private final CreateOrderItemDomainToOrderItemEntityConverter toOrderItemEntityConverter;
    private final OrderItemEntityToOrderItemDomainConverter toOrderItemConverter;
    private final OrderDomainToOrderEntityConverter toOrderEntityConverter;

    @Override
    public void save(OrderItem orderItem) {
        OrderItemEntity orderItemEntity = toOrderItemEntityConverter.convert(orderItem);
        if (ObjectUtils.isEmpty(orderItemEntity)) {
            throw new OrderItemEmptyException("Order Item is empty");
        }
        var savedOrderItemsEntities = orderItemRepository.save(orderItemEntity);
        toOrderItemConverter.convert(savedOrderItemsEntities);
    }

    @Override
    public List<OrderItem> saveAll(List<OrderItem> orderItemsList) {
        List<OrderItemEntity> orderItemEntityList = toOrderItemEntityConverter.convert(orderItemsList);
        var savedOrderItemEntityList = orderItemRepository.saveAll(orderItemEntityList);
        return toOrderItemConverter.convert(savedOrderItemEntityList);
    }

    @Override
    public OrderItem findOrderItemById(Long id) {
        return toOrderItemConverter.convert(orderItemRepository.findById(id)
                .orElseThrow(() -> new OrderItemNotFoundException("Order Item not found for id " + id)));
    }
}
