package br.com.foodwise.platform.infrastructure.rest.dtos.response.orders;

import br.com.foodwise.platform.infrastructure.rest.dtos.response.MenuItemResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponse {
    private Long id;
    private MenuItemResponse menuItem;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
