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
@Table(name = "restaurant_profile")
public class RestaurantProfile {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "business_name", nullable = false)
    private String businessName;

    @Column(name = "description")
    private String description;

    @Column(name = "business_hours")
    private String businessHours;

    @Column(name = "delivery_radius")
    private int deliveryRadius;

    @Column(name = "cuisine_type")
    private String cuisineType;

    @Column(name = "is_open")
    private boolean isOpen;

//  @OneToOne
//  private Menu menu;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

}