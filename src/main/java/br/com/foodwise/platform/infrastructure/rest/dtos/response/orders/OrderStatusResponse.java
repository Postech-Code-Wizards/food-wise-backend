package br.com.foodwise.platform.infrastructure.rest.dtos.response.orders;

import br.com.foodwise.platform.domain.enums.OrderStage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusResponse {
    private Long id;
    private OrderStage orderStage;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
