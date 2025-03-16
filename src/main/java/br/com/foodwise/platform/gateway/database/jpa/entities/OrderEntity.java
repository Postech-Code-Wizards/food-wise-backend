package br.com.foodwise.platform.gateway.database.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerProfileEntity customerProfileEntity;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantProfileEntity restaurantProfileEntity;

    @Column(name = "order_date")
    private ZonedDateTime orderDate;

    @ManyToOne
    @JoinColumn(name = "delivery_to_customer_address_id", nullable = false)
    private AddressEntity addressCustomerEntity;

    @ManyToOne
    @JoinColumn(name = "delivery_from_restaurant_address_id", nullable = false)
    private AddressEntity addressRestaurantEntity;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "transaction_date")
    private ZonedDateTime transactionDate;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "order_status_id", nullable = false)
    private OrderStatusEntity orderStatusEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItemsEntity;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "order_payment_id", nullable = false)
    private OrderPaymentEntity orderPaymentEntity;
}
