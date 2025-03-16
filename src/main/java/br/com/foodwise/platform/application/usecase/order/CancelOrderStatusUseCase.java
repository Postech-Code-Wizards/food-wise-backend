package br.com.foodwise.platform.application.usecase.order;

import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.domain.enums.OrderStage;
import br.com.foodwise.platform.gateway.OrderStatusGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CancelOrderStatusUseCase {
    private final OrderStatusGateway orderStatusGateway;

    public void cancelOrderStatus(Order order) {
        var orderStatus = order.getOrderStatus();
        orderStatus.cancelOrderStatus(OrderStage.CANCELLED);
        orderStatusGateway.save(orderStatus);
    }
}
