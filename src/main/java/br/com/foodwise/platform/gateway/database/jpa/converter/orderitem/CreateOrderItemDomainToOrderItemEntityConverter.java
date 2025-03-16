package br.com.foodwise.platform.gateway.database.jpa.converter.orderitem;

import br.com.foodwise.platform.domain.OrderItem;
import br.com.foodwise.platform.gateway.database.jpa.converter.MenuItemDomainToMenuItemEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.OrderItemEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CreateOrderItemDomainToOrderItemEntityConverter implements Converter<OrderItem, OrderItemEntity> {
    private final MenuItemDomainToMenuItemEntityConverter menuItemDomainToMenuItemEntityConverter;

    @Override
    public OrderItemEntity convert(OrderItem source) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(source, OrderItemEntity.class);
    }

    public List<OrderItemEntity> convert(List<OrderItem> source) {
        ModelMapper modelMapper = new ModelMapper();
        return source.stream().map(orderItem ->
                modelMapper.map(orderItem, OrderItemEntity.class)).collect(Collectors.toList());
    }
}
