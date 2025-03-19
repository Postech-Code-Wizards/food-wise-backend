package br.com.foodwise.platform.domain;

import br.com.foodwise.platform.domain.enums.OrderStage;
import br.com.foodwise.platform.domain.enums.PaymentMethod;
import br.com.foodwise.platform.domain.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Builder
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

    public void updateOrderAddressCustomer(Address address) {
        this.addressCustomer = address;
    }

    public void updateOrderAddressRestaurant(Address address) {
        this.addressRestaurant = address;
    }

    public void updateOrderItems(List<MenuItem> menuItemList) {
        for (MenuItem menuItem : menuItemList) {
            for (OrderItem orderItem : this.orderItems) {
                orderItem.updateMenuItem(menuItem);
            }
        }
    }

    public void updateOrderPayment(PaymentMethod paymentMethod) {
        this.orderPayment.updateOrderPaymentMethod(paymentMethod);
    }

    public void updateOrderTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void cancelOrder() {
        this.orderStatus.cancelOrderStatus(OrderStage.CANCELLED);
        this.orderPayment.cancelOrderPayment(PaymentStatus.REFUNDED);
    }
}
