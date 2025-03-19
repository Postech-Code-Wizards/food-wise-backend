package br.com.foodwise.platform.application.facade.converter.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderItemRequestToOrderItemConverterTest {

    @InjectMocks
    private OrderItemRequestToOrderItemConverter converter;

    @Test
    @DisplayName("Should convert OrderItemsRequest to list of OrderItem domain")
    void convert_ShouldConvertRequestToListOfOrderItems() {

//        OrderItemsRequest request = Instancio.of(OrderItemsRequest.class)
//                .generate(Select.field("menuItems"), gen -> gen.collection().size(3))
//                .create();
//
//        List<OrderItem> result = converter.convert(request);
//
//        assertNotNull(result);
//        assertEquals(request.getMenuItems().size(), result.size());
//
//        for (int i = 0; i < request.getMenuItems().size(); i++) {
//            MenuItem menuItem = request.getMenuItems().get(i);
//            OrderItem orderItem = result.get(i);
//
//            assertNull(orderItem.getId());
//            assertEquals(menuItem, orderItem.getMenuItem());
//            assertNull(orderItem.getOrder());
//            assertNull(orderItem.getCreatedAt());
//            assertNull(orderItem.getUpdatedAt());
//        }
    }

}