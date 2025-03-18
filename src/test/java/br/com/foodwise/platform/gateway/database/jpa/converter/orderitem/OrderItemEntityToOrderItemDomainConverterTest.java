package br.com.foodwise.platform.gateway.database.jpa.converter.orderitem;

import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.domain.OrderItem;
import br.com.foodwise.platform.gateway.database.jpa.converter.MenuItemEntityToDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.orders.OrderEntityToOrderDomainConverter;
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
class OrderItemEntityToOrderItemDomainConverterTest {

    @Mock
    private OrderEntityToOrderDomainConverter orderEntityToOrderDomainConverter;

    @Mock
    private MenuItemEntityToDomainConverter menuItemEntityToDomainConverter;

    @InjectMocks
    private OrderItemEntityToOrderItemDomainConverter converter;

    @Test
    @DisplayName("Should convert OrderItemEntity to OrderItem domain")
    void convert_ShouldConvertOrderItemEntityToOrderItem() {

        OrderItemEntity source = Instancio.create(OrderItemEntity.class);
        Order order = Instancio.create(Order.class);
        MenuItem menuItem = Instancio.create(MenuItem.class);

        when(orderEntityToOrderDomainConverter.convert(source.getOrderEntity())).thenReturn(order);
        when(menuItemEntityToDomainConverter.convert(source.getMenuItemEntity())).thenReturn(menuItem);

        OrderItem result = converter.convert(source);

        assertNotNull(result);
        assertEquals(source.getId(), result.getId());
        assertEquals(order, result.getOrder());
        assertEquals(menuItem, result.getMenuItem());
        assertEquals(source.getCreatedAt(), result.getCreatedAt());
        assertEquals(source.getUpdatedAt(), result.getUpdatedAt());
    }

    @Test
    @DisplayName("Should convert list of OrderItemEntity to list of OrderItem domain")
    void convert_ShouldConvertListOfOrderItemEntitiesToListOfOrderItems() {

        List<OrderItemEntity> sourceList = Instancio.ofList(OrderItemEntity.class).size(3).create();
        Order order = Instancio.create(Order.class);
        MenuItem menuItem = Instancio.create(MenuItem.class);

        when(orderEntityToOrderDomainConverter.convert(org.mockito.ArgumentMatchers.any(OrderEntity.class))).thenReturn(order);
        when(menuItemEntityToDomainConverter.convert(org.mockito.ArgumentMatchers.any(MenuItemEntity.class))).thenReturn(menuItem);

        List<OrderItem> resultList = converter.convert(sourceList);

        assertNotNull(resultList);
        assertEquals(sourceList.size(), resultList.size());
        for (int i = 0; i < sourceList.size(); i++) {
            assertEquals(sourceList.get(i).getId(), resultList.get(i).getId());
            assertEquals(order, resultList.get(i).getOrder());
            assertEquals(menuItem, resultList.get(i).getMenuItem());
            assertEquals(sourceList.get(i).getCreatedAt(), resultList.get(i).getCreatedAt());
            assertEquals(sourceList.get(i).getUpdatedAt(), resultList.get(i).getUpdatedAt());
        }
    }
}