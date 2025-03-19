package br.com.foodwise.platform.gateway.database.jpa.converter.orders;

import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.gateway.database.jpa.converter.AddressDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.CustomerProfileDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.RestaurantProfileDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.orderpayment.CreateOrderPaymentDomainToOrderPaymentEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.orderstatus.CreateOrderStatusToOrderStatusEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDomainToOrderEntityConverter {

    private final CustomerProfileDomainToEntityConverter customerProfileDomainToEntityConverter;
    private final RestaurantProfileDomainToEntityConverter restaurantProfileDomainToEntityConverter;
    private final AddressDomainToEntityConverter addressDomainToEntityConverter;
    private final CreateOrderStatusToOrderStatusEntityConverter createOrderStatusToOrderStatusEntityConverter;
    private final CreateOrderPaymentDomainToOrderPaymentEntityConverter createOrderPaymentDomainToOrderPaymentEntityConverter;

    public OrderEntity convert(Order source) {

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(source.getId());
        orderEntity.setCustomerProfileEntity(customerProfileDomainToEntityConverter.convert(source.getCustomerProfile()));
        orderEntity.setRestaurantProfileEntity(restaurantProfileDomainToEntityConverter.convert(source.getRestaurantProfile()));
        orderEntity.setOrderDate(source.getOrderDate());
        orderEntity.setAddressCustomerEntity(addressDomainToEntityConverter.convert(source.getAddressCustomer()));
        orderEntity.setAddressRestaurantEntity(addressDomainToEntityConverter.convert(source.getAddressRestaurant()));
        orderEntity.setTotalPrice(source.getTotalPrice());
        orderEntity.setTransactionDate(source.getTransactionDate());
        orderEntity.setCreatedAt(null);
        orderEntity.setUpdatedAt(null);
        orderEntity.setOrderStatusEntity(createOrderStatusToOrderStatusEntityConverter.convert(source.getOrderStatus()));
        orderEntity.setOrderPaymentEntity(createOrderPaymentDomainToOrderPaymentEntityConverter.convert(source.getOrderPayment()));

        return  orderEntity;
    }
}
