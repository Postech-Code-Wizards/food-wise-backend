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
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerProfile customerProfile;

    @OneToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantProfile restaurantProfile;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "coutry")
    private String coutry;

    @Column(name = "latitude")
    private float latitude;

    @Column(name = "longitude")
    private float longitude;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

}
