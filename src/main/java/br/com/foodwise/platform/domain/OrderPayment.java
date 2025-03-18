package br.com.foodwise.platform.domain;

import br.com.foodwise.platform.domain.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class OrderPayment {
    private Long id;
    private PaymentStatus paymentStatus;
    private String transactionReference;
    private ZonedDateTime transactionDate;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
