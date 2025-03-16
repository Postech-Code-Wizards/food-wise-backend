package br.com.foodwise.platform.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class Order {

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

    public void updateOrderAddressCustomer(CustomerProfile customerProfile) {
        this.customerProfile = customerProfile;
        this.addressCustomer = customerProfile.getAddress();
    }

    public void updateOrderAddressRestaurant(Address address) {
        this.addressRestaurant = address;
    }

    public void updateOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void updateOrderPayment(OrderPayment orderPayment) {
        this.orderPayment = orderPayment;
    }

    public void updateOrderTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
