package br.com.foodwise.platform.application.usecase.order;

import br.com.foodwise.platform.domain.enums.PaymentMethod;
import br.com.foodwise.platform.gateway.OrderGateway;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.update.order.UpdateOrderPaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateOrderOrderPaymentUseCase {
    private final OrderGateway orderGateway;

    public void updateOrder(Long id, UpdateOrderPaymentRequest request) {
        var order = orderGateway.findById(id);
        order.updateOrderPayment(PaymentMethod.valueOf(request.getPaymentMethod()));
        orderGateway.updateOrderPayment(order);
    }
}
