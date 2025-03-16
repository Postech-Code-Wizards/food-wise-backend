package br.com.foodwise.platform.application.usecase.order;

import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.gateway.OrderGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CancelOrderUseCase {
    private final OrderGateway orderGateway;

    public void cancelOrder(Order order) {
        orderGateway.save(order);
    }
}
