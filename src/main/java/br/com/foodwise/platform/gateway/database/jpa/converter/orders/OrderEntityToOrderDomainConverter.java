package br.com.foodwise.platform.gateway.database.jpa.converter.orders;

import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.gateway.database.jpa.converter.AddressEntityToDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.CustomerProfileEntityToDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.RestaurantProfileEntityToDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.orderitem.OrderItemEntityToOrderItemDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.orderpayment.OrderPaymentEntityToOrderPaymentConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.orderstatus.OrderStatusEntityToOrderStatusDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEntityToOrderDomainConverter {

    private final AddressEntityToDomainConverter addressEntityToDomainConverter;
    private final OrderStatusEntityToOrderStatusDomainConverter orderStatusEntityToOrderStatusDomainConverter;
    private final OrderPaymentEntityToOrderPaymentConverter orderPaymentEntityToOrderPaymentConverter;
    private final CustomerProfileEntityToDomainConverter customerProfileEntityToDomainConverter;
    private final RestaurantProfileEntityToDomainConverter restaurantProfileEntityToDomainConverter;
    private final OrderItemEntityToOrderItemDomainConverter orderItemEntityToOrderItemDomainConverter;

    public Order convert(OrderEntity source) {
        return Order.builder()
                .id(source.getId())
                .customerProfile(customerProfileEntityToDomainConverter.convert(source.getCustomerProfileEntity()))
                .restaurantProfile(restaurantProfileEntityToDomainConverter.convert(source.getRestaurantProfileEntity()))
                .addressCustomer(addressEntityToDomainConverter.convert(source.getAddressCustomerEntity()))
                .addressRestaurant(addressEntityToDomainConverter.convert(source.getAddressRestaurantEntity()))
                .orderDate(source.getOrderDate())
                .orderStatus(orderStatusEntityToOrderStatusDomainConverter.convert(source.getOrderStatusEntity()))
                .orderPayment(orderPaymentEntityToOrderPaymentConverter.convert(source.getOrderPaymentEntity()))
                .createdAt(source.getCreatedAt())
                .updatedAt(source.getUpdatedAt())
                .totalPrice(source.getTotalPrice())
                .transactionDate(source.getTransactionDate())
                .orderItems(orderItemEntityToOrderItemDomainConverter.convert(source.getOrderItemsEntity()))
                .build();
    }
}
