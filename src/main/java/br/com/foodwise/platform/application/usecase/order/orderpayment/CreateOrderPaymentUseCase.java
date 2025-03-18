package br.com.foodwise.platform.application.usecase.order.orderpayment;

import br.com.foodwise.platform.domain.OrderPayment;
import br.com.foodwise.platform.domain.enums.PaymentMethod;
import br.com.foodwise.platform.domain.enums.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOrderPaymentUseCase {

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
    }
}
