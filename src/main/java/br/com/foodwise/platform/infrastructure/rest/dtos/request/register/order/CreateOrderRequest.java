package br.com.foodwise.platform.infrastructure.rest.dtos.request.register.order;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class CreateOrderRequest {
    @NotNull(message = "Customer id is required")
    Long customerProfileId;

    @NotNull(message = "Restaurant id is required")
    Long restaurantProfileId;

    @NotNull(message = "Payment Method must be informed")
    String paymentMethod;

    @DecimalMin(value = "0.0", message = "Total price must be higher than zero")
    BigDecimal totalPrice;

    @NotNull(message = "Must have at least one item to complete the order")
    List<Long> menuItemsIds;
}
