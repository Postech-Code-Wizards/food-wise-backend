package br.com.foodwise.platform.application.usecase.order;

import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.domain.enums.PaymentStatus;
import br.com.foodwise.platform.gateway.OrderPaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CancelOrderPaymentUseCase {
    private final OrderPaymentGateway orderPaymentGateway;

    public void cancelOrderPayment(Order order) {
        var orderPayment = order.getOrderPayment();
        orderPayment.cancelOrderPayment(PaymentStatus.REFUNDED);
        orderPaymentGateway.save(orderPayment);
    }
}
