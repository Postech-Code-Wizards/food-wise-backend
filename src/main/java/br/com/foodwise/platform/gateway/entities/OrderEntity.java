package br.com.foodwise.platform.gateway.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerProfileEntity customerProfileEntity;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantProfileEntity restaurantProfileEntity;

    @OneToOne
    @JoinColumn(name = "order_status_id", nullable = false)
    private OrderStatusEntity orderStatusEntity;

    @Column(name = "order_date", nullable = false)
    private ZonedDateTime orderDate;

    @ManyToOne
    @JoinColumn(name = "delivery_to_customer_address_id", nullable = false)
    private AddressEntity addressEntityCustomer;

    @ManyToOne
    @JoinColumn(name = "delivery_from_restaurant_address_id", nullable = false)
    private AddressEntity addressEntityRestaurant;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "transaction_date")
    private ZonedDateTime transactionDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

}
