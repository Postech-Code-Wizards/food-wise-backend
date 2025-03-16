package br.com.foodwise.platform.gateway.database.jpa.entities;

import br.com.foodwise.platform.domain.enums.PaymentMethod;
import br.com.foodwise.platform.domain.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_payment")
public class OrderPaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private PaymentStatus paymentStatus;

    @Column(name = "transaction_reference", length = 50)
    private String transactionReference;

    @Column(name = "transaction_date")
    private ZonedDateTime transactionDate;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @Column(name = "payment_method")
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

}
