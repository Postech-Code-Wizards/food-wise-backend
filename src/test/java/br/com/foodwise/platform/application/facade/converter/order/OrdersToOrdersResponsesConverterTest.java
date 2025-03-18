package br.com.foodwise.platform.application.facade.converter.order;

import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.orders.OrderResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class OrdersToOrdersResponsesConverterTest {

    @InjectMocks
    private OrdersToOrdersResponsesConverter converter;

    @Test
    @DisplayName("Should convert list of Order domain to list of OrderResponse")
    void convert_ShouldConvertListOfOrdersToListOfOrderResponses() {

        List<Order> source = Instancio.ofList(Order.class).size(3).create();

        List<OrderResponse> result = converter.convert(source);

        assertNotNull(result);
        assertEquals(source.size(), result.size());

        for (int i = 0; i < source.size(); i++) {
            Order order = source.get(i);
            OrderResponse response = result.get(i);

            assertEquals(order.getId(), response.getId());
            assertEquals(order.getCustomerProfile(), response.getCustomerProfile());
            assertEquals(order.getRestaurantProfile(), response.getRestaurantProfile());
            assertEquals(order.getOrderDate(), response.getOrderDate());
            assertEquals(order.getAddressCustomer(), response.getAddressCustomer());
            assertEquals(order.getAddressRestaurant(), response.getAddressRestaurant());
            assertEquals(order.getTotalPrice(), response.getTotalPrice());
            assertEquals(order.getTransactionDate(), response.getTransactionDate());
            assertEquals(order.getCreatedAt(), response.getCreatedAt());
            assertEquals(order.getUpdatedAt(), response.getUpdatedAt());
            assertEquals(order.getOrderStatus(), response.getOrderStatus());
            assertEquals(order.getOrderItems(), response.getOrderItems());
            assertEquals(order.getOrderPayment(), response.getOrderPayment());
        }
    }

}