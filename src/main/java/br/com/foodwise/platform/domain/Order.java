package br.com.foodwise.platform.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class Order {
    private Long id;
    private CustomerProfile customerProfile;
    private RestaurantProfile restaurantProfile;
    private OrderStatus orderStatus;
    private ZonedDateTime orderDate;
    private Address addressCustomer;
    private Address addressRestaurant;
    private BigDecimal totalPrice;
    private ZonedDateTime transactionDate;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
