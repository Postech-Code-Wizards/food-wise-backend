package br.com.foodwise.platform.infrastructure.rest.dtos.request.update.order;

import br.com.foodwise.platform.domain.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateOrderPaymentRequest {
    @NotNull(message = "Must have a value to update Payment Method")
    private String paymentMethod;
}
