package br.com.foodwise.platform.application.usecase.order;

import br.com.foodwise.platform.domain.OrderPayment;
import br.com.foodwise.platform.gateway.OrderGateway;
import br.com.foodwise.platform.gateway.OrderPaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateOrderOrderPaymentUseCase {
    private final OrderGateway orderGateway;
    private final OrderPaymentGateway orderPaymentGateway;

    public void updateOrder(Long id, OrderPayment orderPaymentData) {
        var order = orderGateway.findById(id);
        var orderPayment = order.getOrderPayment();

        orderPayment.updateOrderPaymentMethod(orderPaymentData.getPaymentMethod());
        order.updateOrderPayment(orderPayment);

        orderPaymentGateway.save(orderPayment);
        orderGateway.save(order);
    }
}
