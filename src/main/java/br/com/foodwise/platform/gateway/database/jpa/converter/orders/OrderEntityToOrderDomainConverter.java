package br.com.foodwise.platform.gateway.database.jpa.converter.orders;

import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.gateway.database.jpa.entities.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEntityToOrderDomainConverter implements Converter<OrderEntity, Order> {

    @Override
    public Order convert(OrderEntity source) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(source, Order.class);
    }
}
