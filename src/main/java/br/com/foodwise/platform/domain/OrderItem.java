package br.com.foodwise.platform.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class OrderItem {
    private Long id;
    private MenuItem menuItem;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private Order order;

    public void updateMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }
}
