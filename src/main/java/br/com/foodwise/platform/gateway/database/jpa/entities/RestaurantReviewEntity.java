package br.com.foodwise.platform.gateway.database.jpa.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "restaurant_review")
public class RestaurantReviewEntity {

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

    @Column(name = "rating", nullable = false)
    private Short rating;

    @Column(name = "review_text")
    private String reviewText;

    @Column(name = "created_at", updatable = false, nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

}
