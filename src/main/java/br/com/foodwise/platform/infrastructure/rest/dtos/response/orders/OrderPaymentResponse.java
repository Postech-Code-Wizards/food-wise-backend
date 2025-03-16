package br.com.foodwise.platform.infrastructure.rest.dtos.response.orders;

import br.com.foodwise.platform.domain.enums.PaymentMethod;
import br.com.foodwise.platform.domain.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPaymentResponse {
    private Long id;
    private PaymentStatus paymentStatus;
    private String transactionReference;
    private ZonedDateTime transactionDate;
    private PaymentMethod paymentMethod;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
