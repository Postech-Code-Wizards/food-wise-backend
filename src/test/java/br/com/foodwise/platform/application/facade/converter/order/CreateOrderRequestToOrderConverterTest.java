package br.com.foodwise.platform.application.facade.converter.order;

import br.com.foodwise.platform.domain.CustomerProfile;
import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.domain.enums.OrderStage;
import br.com.foodwise.platform.domain.enums.PaymentMethod;
import br.com.foodwise.platform.domain.enums.PaymentStatus;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.order.CreateOrderRequest;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CreateOrderRequestToOrderConverterTest {

    private final CreateOrderRequestToOrderConverter converter = new CreateOrderRequestToOrderConverter();

    @Test
    @DisplayName("Should convert CreateOrderRequest to Order domain")
    void convert_ShouldConvertRequestToOrder() {

        CreateOrderRequest request = Instancio.of(CreateOrderRequest.class)
                .set(Select.field("paymentMethod"), PaymentMethod.CREDIT_CARD.toString())
                .create();

        CustomerProfile customerProfile = Instancio.create(CustomerProfile.class);
        RestaurantProfile restaurantProfile = Instancio.create(RestaurantProfile.class);

        Order result = converter.convert(request, customerProfile, restaurantProfile);

        assertNull(result.getId());
        assertEquals(customerProfile, result.getCustomerProfile());
        assertEquals(restaurantProfile, result.getRestaurantProfile());
        assertNotNull(result.getOrderDate());
        assertEquals(customerProfile.getAddress(), result.getAddressCustomer());
        assertEquals(restaurantProfile.getAddress(), result.getAddressRestaurant());
        assertEquals(request.getTotalPrice(), result.getTotalPrice());
        assertNull(result.getTransactionDate());
        assertNull(result.getCreatedAt());
        assertNull(result.getUpdatedAt());
        assertNotNull(result.getOrderStatus());
        assertEquals(OrderStage.PENDING, result.getOrderStatus().getOrderStage());
        assertEquals(new ArrayList<>(), result.getOrderItems());
        assertNotNull(result.getOrderPayment());
        assertEquals(PaymentStatus.PENDING, result.getOrderPayment().getPaymentStatus());
        assertEquals(PaymentMethod.valueOf(request.getPaymentMethod()), result.getOrderPayment().getPaymentMethod());
    }

}