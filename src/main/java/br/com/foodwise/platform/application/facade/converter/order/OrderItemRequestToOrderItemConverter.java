package br.com.foodwise.platform.application.facade.converter.order;

import br.com.foodwise.platform.domain.OrderItem;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.update.order.UpdateOrderItemsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderItemRequestToOrderItemConverter implements Converter<UpdateOrderItemsRequest, List<OrderItem>> {
    @Override
    public List<OrderItem> convert(UpdateOrderItemsRequest updateOrderItemsRequests) {
        return updateOrderItemsRequests.getMenuItemsIds().stream().map(menuItem ->
                new OrderItem(
                        null,
                        null,
                        null,
                        null,
                        null
                )
        ).toList();
    }
}
