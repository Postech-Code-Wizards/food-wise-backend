package br.com.foodwise.platform.application.facade.converter.order;

import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.orders.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderToOrderResponseConverter implements Converter<Order, OrderResponse> {
    @Override
    public OrderResponse convert(Order source) {
        return new OrderResponse(
                source.getId(),
                source.getCustomerProfile(),
                source.getRestaurantProfile(),
                source.getOrderDate(),
                source.getAddressCustomer(),
                source.getAddressRestaurant(),
                source.getTotalPrice(),
                source.getTransactionDate(),
                source.getCreatedAt(),
                source.getUpdatedAt(),
                source.getOrderStatus(),
                source.getOrderItems(),
                source.getOrderPayment()
        );
    }
}
