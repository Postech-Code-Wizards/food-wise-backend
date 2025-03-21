package br.com.foodwise.platform.gateway.database.jpa.entities;

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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer_profile")
public class CustomerProfileEntity {

    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "first_name", nullable = false, length = 1000)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private AddressEntity addressEntity;

    @CreationTimestamp
    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phone_id", nullable = false)
    private PhoneEntity phoneEntity;

}
