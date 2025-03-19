package br.com.foodwise.platform.gateway.database.jpa.converter.orderstatus;

import br.com.foodwise.platform.domain.OrderStatus;
import br.com.foodwise.platform.gateway.database.jpa.entities.OrderStatusEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class CreateOrderStatusToOrderStatusEntityConverterTest {

    @InjectMocks
    private CreateOrderStatusToOrderStatusEntityConverter converter;

    @Test
    @DisplayName("Should convert OrderStatus domain to OrderStatusEntity")
    void convert_ShouldConvertOrderStatusToOrderStatusEntity() {

        OrderStatus source = Instancio.create(OrderStatus.class);

        OrderStatusEntity result = converter.convert(source);

        assertEquals(source.getId(), result.getId());
        assertEquals(source.getOrderStage(), result.getOrderStage());
        assertNull(result.getCreatedAt());
    }

}