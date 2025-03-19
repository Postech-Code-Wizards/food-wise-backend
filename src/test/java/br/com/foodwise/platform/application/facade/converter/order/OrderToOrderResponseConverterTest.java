package br.com.foodwise.platform.application.facade.converter.order;

import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.orders.OrderResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class OrderToOrderResponseConverterTest {

    @InjectMocks
    private OrderToOrderResponseConverter converter;

    @Test
    @DisplayName("Should convert Order domain to OrderResponse")
    void convert_ShouldConvertOrderToOrderResponse() {

        Order source = Instancio.create(Order.class);

        OrderResponse result = converter.convert(source);

        assertEquals(source.getId(), result.getId());
        assertEquals(source.getCustomerProfile(), result.getCustomerProfile());
        assertEquals(source.getRestaurantProfile(), result.getRestaurantProfile());
        assertEquals(source.getOrderDate(), result.getOrderDate());
        assertEquals(source.getAddressCustomer(), result.getAddressCustomer());
        assertEquals(source.getAddressRestaurant(), result.getAddressRestaurant());
        assertEquals(source.getTotalPrice(), result.getTotalPrice());
        assertEquals(source.getTransactionDate(), result.getTransactionDate());
        assertEquals(source.getCreatedAt(), result.getCreatedAt());
        assertEquals(source.getUpdatedAt(), result.getUpdatedAt());
        assertEquals(source.getOrderStatus(), result.getOrderStatus());
        assertEquals(source.getOrderItems(), result.getOrderItems());
        assertEquals(source.getOrderPayment(), result.getOrderPayment());
    }
}