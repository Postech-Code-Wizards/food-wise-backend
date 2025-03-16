package br.com.foodwise.platform.gateway.database.jpa.converter.orders;

import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.gateway.database.jpa.entities.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDomainToOrderEntityConverter implements Converter<Order, OrderEntity> {
    @Override
    public OrderEntity convert(Order source) {
        ModelMapper modelMapper = new ModelMapper();
        var orderEntity = modelMapper.map(source, OrderEntity.class);
        converted(orderEntity);
        return converted(orderEntity);
    }

    private OrderEntity converted(OrderEntity source) {
        return new OrderEntity(
                source.getId(),
                source.getCustomerProfileEntity(),
                source.getRestaurantProfileEntity(),
                source.getOrderDate(),
                source.getAddressCustomerEntity(),
                source.getAddressRestaurantEntity(),
                source.getTotalPrice(),
                null,
                null,
                null,
                source.getOrderStatusEntity(),
                null,
                source.getOrderPaymentEntity()
        );
    }
}
