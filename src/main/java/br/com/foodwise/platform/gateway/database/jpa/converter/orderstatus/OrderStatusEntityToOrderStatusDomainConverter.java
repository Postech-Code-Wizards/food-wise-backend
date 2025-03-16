package br.com.foodwise.platform.gateway.database.jpa.converter.orderstatus;

import br.com.foodwise.platform.domain.OrderStatus;
import br.com.foodwise.platform.gateway.database.jpa.entities.OrderStatusEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderStatusEntityToOrderStatusDomainConverter implements Converter<OrderStatusEntity, OrderStatus> {

    @Override
    public OrderStatus convert(OrderStatusEntity source) {
        return new OrderStatus(
                source.getId(),
                source.getOrderStage(),
                source.getCreatedAt(),
                source.getUpdatedAt()
        );
    }
}
