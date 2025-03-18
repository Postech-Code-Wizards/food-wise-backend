package br.com.foodwise.platform.application.facade.converter.order;

import br.com.foodwise.platform.domain.OrderPayment;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.order.OrderPaymentRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class OrderPaymentRequestToOrderPaymentConverterTest {

    @InjectMocks
    private OrderPaymentRequestToOrderPaymentConverter converter;

    @Test
    @DisplayName("Should convert OrderPaymentRequest to OrderPayment domain")
    void convert_ShouldConvertRequestToOrderPayment() {

        OrderPaymentRequest request = Instancio.create(OrderPaymentRequest.class);

        OrderPayment result = converter.convert(request);

        // Assert
        assertNull(result.getId());
        assertNull(result.getPaymentStatus());
        assertNull(result.getTransactionReference());
        assertNull(result.getTransactionDate());
        assertNull(result.getCreatedAt());
        assertNull(result.getUpdatedAt());
        assertEquals(request.getPaymentMethod(), result.getPaymentMethod());
    }

}