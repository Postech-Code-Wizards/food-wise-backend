package br.com.foodwise.platform.infrastructure.rest.dtos.request.register.order;

import br.com.foodwise.platform.domain.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderItemsRequest {
    List<MenuItem> menuItems;
}
