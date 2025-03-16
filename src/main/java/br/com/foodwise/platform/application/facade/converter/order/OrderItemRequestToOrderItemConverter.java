package br.com.foodwise.platform.application.facade.converter.order;

import br.com.foodwise.platform.domain.OrderItem;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.order.OrderItemsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderItemRequestToOrderItemConverter implements Converter<OrderItemsRequest, List<OrderItem>> {
    @Override
    public List<OrderItem> convert(OrderItemsRequest orderItemsRequests) {
        return orderItemsRequests.getMenuItems().stream().map(menuItem ->
                new OrderItem(
                        null,
                        menuItem,
                        null,
                        null,
                        null
                )
        ).toList();
    }
}
