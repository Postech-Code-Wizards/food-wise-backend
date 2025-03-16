package br.com.foodwise.platform.gateway.database.jpa.converter.orderitem;

import br.com.foodwise.platform.domain.OrderItem;
import br.com.foodwise.platform.gateway.database.jpa.entities.OrderItemEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderItemEntityToOrderItemDomainConverter implements Converter<List<OrderItemEntity>, List<OrderItem>> {

    @Override
    public List<OrderItem> convert(List<OrderItemEntity> source) {
        ModelMapper modelMapper = new ModelMapper();
        return source.stream().map(orderItem ->
                modelMapper.map(orderItem, OrderItem.class)).collect(Collectors.toList());


    }

    public OrderItem convert(OrderItemEntity source) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(source, OrderItem.class);
    }
}
