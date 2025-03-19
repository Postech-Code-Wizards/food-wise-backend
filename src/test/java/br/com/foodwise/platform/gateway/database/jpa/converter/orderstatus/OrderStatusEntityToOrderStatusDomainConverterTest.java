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

@ExtendWith(MockitoExtension.class)
class OrderStatusEntityToOrderStatusDomainConverterTest {

    @InjectMocks
    private OrderStatusEntityToOrderStatusDomainConverter converter;

    @Test
    @DisplayName("Should convert OrderStatusEntity to OrderStatus domain")
    void convert_ShouldConvertOrderStatusEntityToOrderStatus() {

        OrderStatusEntity source = Instancio.create(OrderStatusEntity.class);

        OrderStatus result = converter.convert(source);

        assertEquals(source.getId(), result.getId());
        assertEquals(source.getOrderStage(), result.getOrderStage());
        assertEquals(source.getCreatedAt(), result.getCreatedAt());
        assertEquals(source.getUpdatedAt(), result.getUpdatedAt());
    }

}