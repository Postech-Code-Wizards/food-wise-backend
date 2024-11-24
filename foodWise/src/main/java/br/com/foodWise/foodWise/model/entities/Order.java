package br.com.foodWise.foodWise.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

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
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerProfile customerProfile;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantProfile restaurantProfile;

    @OneToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;

    @Column(name = "order_date")
    private OffsetDateTime orderDate;

    @OneToOne
    @JoinColumn(name = "order_payment_id")
    private OrderPayment orderPayment;

    @OneToMany
    @JoinColumn(name = "delivery_to_customer_address_id")
    private Address address;

    @OneToMany
    @JoinColumn(name = "delivery_from_restaurant_address_id")
    private Address addressRestaurant;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "placed_at")
    private OffsetDateTime placedAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

}
