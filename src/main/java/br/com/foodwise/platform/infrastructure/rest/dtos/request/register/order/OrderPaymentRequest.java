package br.com.foodwise.platform.infrastructure.rest.dtos.request.register.order;

import br.com.foodwise.platform.domain.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderPaymentRequest {
    private PaymentMethod paymentMethod;
}
