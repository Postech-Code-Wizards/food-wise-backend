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
class CreateOrderPaymentDomainToOrderPaymentEntityConverterTest {

    @InjectMocks
    private CreateOrderPaymentDomainToOrderPaymentEntityConverter converter;

    @Test
    @DisplayName("Should convert OrderPayment domain to OrderPaymentEntity")
    void convert_ShouldConvertOrderPaymentToOrderPaymentEntity() {

        OrderPayment source = Instancio.create(OrderPayment.class);

        OrderPaymentEntity result = converter.convert(source);

        assertEquals(source.getId(), result.getId());
        assertEquals(source.getPaymentStatus(), result.getPaymentStatus());
        assertEquals(source.getTransactionReference(), result.getTransactionReference());
        assertEquals(source.getTransactionDate(), result.getTransactionDate());
        assertEquals(source.getPaymentMethod(), result.getPaymentMethod());
    }

}