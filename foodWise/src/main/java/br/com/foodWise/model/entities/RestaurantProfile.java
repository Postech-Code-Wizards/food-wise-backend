package br.com.foodwise.model.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "restaurant_profile")
public class RestaurantProfile {

    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "business_name", nullable = false, length = 100)
    private String businessName;

    @Column(name = "description")
    private String description;

    @Column(name = "business_hours", length = 100)
    private String businessHours;

    @Column(name = "delivery_radius")
    private Short deliveryRadius;

    @Column(name = "cuisine_type", length = 50)
    private String cuisineType;

    @Column(name = "is_open", nullable = false)
    private boolean isOpen = false;

    @CreationTimestamp
    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phone_id", nullable = false)
    private Phone phone;

}
