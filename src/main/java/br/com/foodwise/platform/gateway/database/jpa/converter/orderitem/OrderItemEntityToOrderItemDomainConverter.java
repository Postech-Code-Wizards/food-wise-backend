package br.com.foodwise.platform.gateway.database.jpa.converter.orderitem;

import br.com.foodwise.platform.domain.OrderItem;
import br.com.foodwise.platform.gateway.database.jpa.converter.MenuItemEntityToDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.OrderItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class OrderItemEntityToOrderItemDomainConverter {

    private final MenuItemEntityToDomainConverter menuItemEntityToDomainConverter;

    public List<OrderItem> convert(List<OrderItemEntity> source) {
        if(Objects.isNull(source)){
            return List.of();
        }
        return source.stream().map(this::convert).toList();
    }

    public OrderItem convert(OrderItemEntity source) {
        return OrderItem.builder()
                .id(source.getId())
                .menuItem(menuItemEntityToDomainConverter.convert(source.getMenuItemEntity()))
                .createdAt(source.getCreatedAt())
                .updatedAt(source.getUpdatedAt())
                .build();
    }
}
