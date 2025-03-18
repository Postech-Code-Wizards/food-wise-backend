package br.com.foodwise.platform.gateway.database.jpa.converter.orderitem;

import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.domain.OrderItem;
import br.com.foodwise.platform.gateway.database.jpa.converter.MenuItemDomainToMenuItemEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.orders.OrderDomainToOrderEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.MenuItemEntity;
import br.com.foodwise.platform.gateway.database.jpa.entities.OrderEntity;
import br.com.foodwise.platform.gateway.database.jpa.entities.OrderItemEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateOrderItemDomainToOrderItemEntityConverterTest {

    @Mock
    private OrderDomainToOrderEntityConverter toOrderEntityConverter;

    @Mock
    private MenuItemDomainToMenuItemEntityConverter menuItemDomainToMenuItemEntityConverter;

    @InjectMocks
    private CreateOrderItemDomainToOrderItemEntityConverter converter;

    @Test
    @DisplayName("Should convert OrderItem domain to OrderItemEntity")
    void convert_ShouldConvertOrderItemToOrderItemEntity() {

        OrderItem source = Instancio.create(OrderItem.class);
        OrderEntity orderEntity = Instancio.create(OrderEntity.class);
        MenuItemEntity menuItemEntity = Instancio.create(MenuItemEntity.class);

        when(toOrderEntityConverter.convert(source.getOrder())).thenReturn(orderEntity);
        when(menuItemDomainToMenuItemEntityConverter.convert(source.getMenuItem())).thenReturn(menuItemEntity);

        OrderItemEntity result = converter.convert(source);

        assertNotNull(result);
        assertEquals(orderEntity, result.getOrderEntity());
        assertEquals(menuItemEntity, result.getMenuItemEntity());
        assertEquals(source.getCreatedAt(), result.getCreatedAt());
        assertEquals(source.getUpdatedAt(), result.getUpdatedAt());
    }

    @Test
    @DisplayName("Should convert list of OrderItem domain to list of OrderItemEntity")
    void convert_ShouldConvertListOfOrderItemsToListOfOrderItemEntities() {

        List<OrderItem> sourceList = Instancio.ofList(OrderItem.class).size(3).create();
        OrderEntity orderEntity = Instancio.create(OrderEntity.class);
        MenuItemEntity menuItemEntity = Instancio.create(MenuItemEntity.class);

        when(toOrderEntityConverter.convert(org.mockito.ArgumentMatchers.any(Order.class))).thenReturn(orderEntity);
        when(menuItemDomainToMenuItemEntityConverter.convert(org.mockito.ArgumentMatchers.any(MenuItem.class))).thenReturn(menuItemEntity);

        List<OrderItemEntity> resultList = converter.convert(sourceList);

        assertNotNull(resultList);
        assertEquals(sourceList.size(), resultList.size());
        for (int i = 0; i < sourceList.size(); i++) {
            assertEquals(orderEntity, resultList.get(i).getOrderEntity());
            assertEquals(menuItemEntity, resultList.get(i).getMenuItemEntity());
            assertEquals(sourceList.get(i).getCreatedAt(), resultList.get(i).getCreatedAt());
            assertEquals(sourceList.get(i).getUpdatedAt(), resultList.get(i).getUpdatedAt());
        }
    }
}