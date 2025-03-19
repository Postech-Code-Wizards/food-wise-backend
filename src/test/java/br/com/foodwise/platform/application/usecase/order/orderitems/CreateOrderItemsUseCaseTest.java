package br.com.foodwise.platform.application.usecase.order.orderitems;

import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.domain.OrderItem;
import br.com.foodwise.platform.gateway.OrderItemGateway;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateOrderItemsUseCaseTest {

    @Mock
    private OrderItemGateway orderItemGateway;

    @InjectMocks
    private CreateOrderItemsUseCase useCase;

    @Test
    @DisplayName("Should create order items successfully")
    void create_ShouldCreateOrderItems() {

        List<MenuItem> menuItems = Instancio.ofList(MenuItem.class).size(3).create();
        Order order = Instancio.create(Order.class);
        List<OrderItem> expectedOrderItems = menuItems.stream()
                .map(menuItem -> OrderItem.builder().menuItem(menuItem).order(order).build())
                .toList();

        when(orderItemGateway.saveAll(any())).thenReturn(expectedOrderItems);

        List<OrderItem> result = useCase.create(menuItems, order);

        assertNotNull(result);
        assertEquals(expectedOrderItems, result);
    }

}