package br.com.foodwise.platform.gateway.database.jpa.converter.orderstatus;

import br.com.foodwise.platform.domain.OrderStatus;
import br.com.foodwise.platform.gateway.database.jpa.entities.OrderStatusEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateOrderStatusToOrderStatusEntityConverter implements Converter<OrderStatus, OrderStatusEntity> {

    @Override
    public OrderStatusEntity convert(OrderStatus source) {
        return new OrderStatusEntity(
                source.getId(),
                source.getOrderStage(),
                null,
                null
        );
    }
}
