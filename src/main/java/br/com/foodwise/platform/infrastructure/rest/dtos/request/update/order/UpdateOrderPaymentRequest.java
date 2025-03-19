package br.com.foodwise.platform.infrastructure.rest.dtos.request.update.order;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderPaymentRequest {
    @NotNull(message = "Must have a value to update Payment Method")
    private String paymentMethod;
}
