package br.com.foodwise.platform.domain;

import br.com.foodwise.platform.domain.enums.OrderStage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class OrderStatus {

    private Long id;
    private OrderStage orderStage;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public void cancelOrderStatus(OrderStage orderStage) {
        this.orderStage = orderStage;
    }
}
