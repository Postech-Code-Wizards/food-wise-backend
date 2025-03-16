package br.com.foodwise.platform.application.usecase.order;

import br.com.foodwise.platform.gateway.OrderGateway;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.order.UpdateOrderTotalPriceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateOrderTotalPriceUseCase {
    private final OrderGateway orderGateway;

    public void updateOrder(Long id, UpdateOrderTotalPriceRequest totalPriceRequest) {
        var order = orderGateway.findById(id);
        order.updateOrderTotalPrice(totalPriceRequest.getTotalPrice());
        orderGateway.save(order);
    }
}
