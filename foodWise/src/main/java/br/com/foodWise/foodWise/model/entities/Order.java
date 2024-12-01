package br.com.foodWise.foodWise.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerProfile customerProfile;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantProfile restaurantProfile;

    @OneToOne
    @JoinColumn(name = "order_status_id", nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "order_date", nullable = false)
    private ZonedDateTime orderDate;

    @ManyToOne
    @JoinColumn(name = "delivery_to_customer_address_id", nullable = false)
    private Address addressCustomer;

    @ManyToOne
    @JoinColumn(name = "delivery_from_restaurant_address_id", nullable = false)
    private Address addressRestaurant;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "transaction_date")
    private ZonedDateTime transactionDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

}
