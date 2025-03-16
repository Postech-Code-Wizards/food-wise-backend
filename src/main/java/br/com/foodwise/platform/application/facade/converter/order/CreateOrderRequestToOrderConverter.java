package br.com.foodwise.platform.application.facade.converter.order;

import br.com.foodwise.platform.domain.*;
import br.com.foodwise.platform.domain.enums.OrderStage;
import br.com.foodwise.platform.domain.enums.PaymentMethod;
import br.com.foodwise.platform.domain.enums.PaymentStatus;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.order.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class CreateOrderRequestToOrderConverter {

    public Order convert(CreateOrderRequest request, CustomerProfile customerProfile, RestaurantProfile restaurantProfile) {
        var addressCustomer = customerProfile.getAddress();
        var addressRestaurant = restaurantProfile.getAddress();
        var totalPrice = request.getTotalPrice();

        return new Order(
                null,
                customerProfile,
                restaurantProfile,
                ZonedDateTime.now(),
                addressCustomer,
                addressRestaurant,
                totalPrice,
                null,
                null,
                null,
                createOrderStatus(),
                new ArrayList<>(),
                createOrderPayment(request.getPaymentMethod())
        );
    }

    private OrderStatus createOrderStatus() {
        return new OrderStatus(
                null,
                OrderStage.PENDING,
                null,
                null
        );
    }

    private OrderPayment createOrderPayment(String paymentMethod) {
        return new OrderPayment(
                null,
                PaymentStatus.PENDING,
                null,
                null,
                null,
                null,
                PaymentMethod.valueOf(paymentMethod)
        );
    }
}
