package br.com.foodwise.platform.gateway.database.jpa.converter.orderpayment;

import br.com.foodwise.platform.domain.OrderPayment;
import br.com.foodwise.platform.gateway.database.jpa.entities.OrderPaymentEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class OrderPaymentEntityToOrderPaymentConverterTest {

    @InjectMocks
    private OrderPaymentEntityToOrderPaymentConverter converter;

    @Test
    @DisplayName("Should convert OrderPaymentEntity to OrderPayment domain")
    void convert_ShouldConvertOrderPaymentEntityToOrderPayment() {

        OrderPaymentEntity source = Instancio.create(OrderPaymentEntity.class);

        OrderPayment result = converter.convert(source);

        assertEquals(source.getId(), result.getId());
        assertEquals(source.getPaymentStatus(), result.getPaymentStatus());
        assertEquals(source.getTransactionReference(), result.getTransactionReference());
        assertEquals(source.getTransactionDate(), result.getTransactionDate());
        assertEquals(source.getCreatedAt(), result.getCreatedAt());
        assertEquals(source.getUpdatedAt(), result.getUpdatedAt());
        assertEquals(source.getPaymentMethod(), result.getPaymentMethod());
    }

}