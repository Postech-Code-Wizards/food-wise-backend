package br.com.foodwise.platform.gateway.database.jpa.converter.orderitem;

import br.com.foodwise.platform.domain.OrderItem;
import br.com.foodwise.platform.gateway.database.jpa.converter.MenuItemDomainToMenuItemEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.orders.OrderDomainToOrderEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.OrderItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateOrderItemDomainToOrderItemEntityConverter {

    private final OrderDomainToOrderEntityConverter toOrderEntityConverter;
    private final MenuItemDomainToMenuItemEntityConverter menuItemDomainToMenuItemEntityConverter;

    public OrderItemEntity convert(OrderItem source) {
        var orderItemEntity = new OrderItemEntity();
        orderItemEntity.setOrderEntity(toOrderEntityConverter.convert(source.getOrder()));
        orderItemEntity.setMenuItemEntity(menuItemDomainToMenuItemEntityConverter.convert(source.getMenuItem()));
        orderItemEntity.setCreatedAt(source.getCreatedAt());
        orderItemEntity.setUpdatedAt(source.getUpdatedAt());
        return orderItemEntity;
    }

    public List<OrderItemEntity> convert(List<OrderItem> source) {
        return source.stream().map(this::convert).toList();
    }
}
