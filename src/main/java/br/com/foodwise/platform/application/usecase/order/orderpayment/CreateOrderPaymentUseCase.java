package br.com.foodwise.platform.application.usecase.order.orderpayment;

import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.domain.OrderPayment;
import br.com.foodwise.platform.domain.enums.PaymentMethod;
import br.com.foodwise.platform.domain.enums.PaymentStatus;
import br.com.foodwise.platform.gateway.OrderPaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOrderPaymentUseCase {
//    private final OrderPaymentGateway orderPaymentGateway;

    public OrderPayment create(String paymentMethod) {
        return new OrderPayment(
                null,
                PaymentStatus.PENDING,
                null,
                null,
                null,
                null,
                PaymentMethod.valueOf(paymentMethod)
        );
//        return orderPaymentGateway.save(orderPayment);
    }
}
