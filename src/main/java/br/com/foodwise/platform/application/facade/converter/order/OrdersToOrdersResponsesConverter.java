package br.com.foodwise.platform.application.facade.converter.order;

import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.orders.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrdersToOrdersResponsesConverter implements Converter<List<Order>, List<OrderResponse>> {
    @Override
    public List<OrderResponse> convert(List<Order> source) {
        return source.stream().map(order ->
            new OrderResponse(
                    order.getId(),
                    order.getCustomerProfile(),
                    order.getRestaurantProfile(),
                    order.getOrderDate(),
                    order.getAddressCustomer(),
                    order.getAddressRestaurant(),
                    order.getTotalPrice(),
                    order.getTransactionDate(),
                    order.getCreatedAt(),
                    order.getUpdatedAt(),
                    order.getOrderStatus(),
                    order.getOrderItems(),
                    order.getOrderPayment()
            )
        ).toList();
    }
}
