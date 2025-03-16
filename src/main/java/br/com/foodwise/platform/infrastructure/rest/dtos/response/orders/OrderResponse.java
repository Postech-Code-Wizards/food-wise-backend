package br.com.foodwise.platform.infrastructure.rest.dtos.response.orders;

import br.com.foodwise.platform.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private CustomerProfile customerProfile;
    private RestaurantProfile restaurantProfile;
    private ZonedDateTime orderDate;
    private Address addressCustomer;
    private Address addressRestaurant;
    private BigDecimal totalPrice;
    private ZonedDateTime transactionDate;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private OrderStatus orderStatus;
    private List<OrderItem> orderItems;
    private OrderPayment orderPayment;
}
