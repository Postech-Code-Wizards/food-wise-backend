package br.com.foodwise.platform.infrastructure.rest.dtos.request.update.order;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UpdateOrderItemsRequest {
    @NotNull(message = "Must have at least one item to update")
    List<Long> menuItemsIds;
}
