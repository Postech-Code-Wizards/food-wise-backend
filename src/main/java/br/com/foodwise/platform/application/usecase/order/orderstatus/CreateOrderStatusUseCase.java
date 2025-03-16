package br.com.foodwise.platform.application.usecase.order.orderstatus;

import br.com.foodwise.platform.domain.OrderStatus;
import br.com.foodwise.platform.domain.enums.OrderStage;
import br.com.foodwise.platform.gateway.OrderStatusGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOrderStatusUseCase {
//    private final OrderStatusGateway orderStatusGateway;

    public OrderStatus create(OrderStage orderStage) {
        return new OrderStatus(
                null,
                orderStage,
                null,
                null
        );
//        return orderStatusGateway.save(orderStatus);
    }
}
