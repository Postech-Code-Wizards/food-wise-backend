package br.com.foodwise.platform.infrastructure.rest.dtos.request.update.order;

import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class UpdateOrderTotalPriceRequest {
    @DecimalMin(value = "0.0", message = "Total price must be higher than zero")
    private BigDecimal totalPrice;
}
