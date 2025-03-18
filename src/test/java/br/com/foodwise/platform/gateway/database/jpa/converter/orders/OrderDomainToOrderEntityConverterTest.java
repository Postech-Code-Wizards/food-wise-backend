package br.com.foodwise.platform.gateway.database.jpa.converter.orders;

import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.gateway.database.jpa.converter.AddressDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.CustomerProfileDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.RestaurantProfileDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.orderpayment.CreateOrderPaymentDomainToOrderPaymentEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.orderstatus.CreateOrderStatusToOrderStatusEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.*;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderDomainToOrderEntityConverterTest {

    @Mock
    private CustomerProfileDomainToEntityConverter customerProfileDomainToEntityConverter;

    @Mock
    private RestaurantProfileDomainToEntityConverter restaurantProfileDomainToEntityConverter;

    @Mock
    private AddressDomainToEntityConverter addressDomainToEntityConverter;

    @Mock
    private CreateOrderStatusToOrderStatusEntityConverter createOrderStatusToOrderStatusEntityConverter;

    @Mock
    private CreateOrderPaymentDomainToOrderPaymentEntityConverter createOrderPaymentDomainToOrderPaymentEntityConverter;

    @InjectMocks
    private OrderDomainToOrderEntityConverter converter;

    @Test
    @DisplayName("Should convert Order domain to OrderEntity")
    void convert_ShouldConvertOrderToOrderEntity() {

        Order source = Instancio.create(Order.class);
        CustomerProfileEntity customerProfileEntity = Instancio.create(CustomerProfileEntity.class);
        RestaurantProfileEntity restaurantProfileEntity = Instancio.create(RestaurantProfileEntity.class);
        AddressEntity addressCustomerEntity = Instancio.create(AddressEntity.class);
        AddressEntity addressRestaurantEntity = Instancio.create(AddressEntity.class);
        OrderStatusEntity orderStatusEntity = Instancio.create(OrderStatusEntity.class);
        OrderPaymentEntity orderPaymentEntity = Instancio.create(OrderPaymentEntity.class);

        when(customerProfileDomainToEntityConverter.convert(source.getCustomerProfile())).thenReturn(customerProfileEntity);
        when(restaurantProfileDomainToEntityConverter.convert(source.getRestaurantProfile())).thenReturn(restaurantProfileEntity);
        when(addressDomainToEntityConverter.convert(source.getAddressCustomer())).thenReturn(addressCustomerEntity);
        when(addressDomainToEntityConverter.convert(source.getAddressRestaurant())).thenReturn(addressRestaurantEntity);
        when(createOrderStatusToOrderStatusEntityConverter.convert(source.getOrderStatus())).thenReturn(orderStatusEntity);
        when(createOrderPaymentDomainToOrderPaymentEntityConverter.convert(source.getOrderPayment())).thenReturn(orderPaymentEntity);

        OrderEntity result = converter.convert(source);

        assertEquals(source.getId(), result.getId());
        assertEquals(customerProfileEntity, result.getCustomerProfileEntity());
        assertEquals(restaurantProfileEntity, result.getRestaurantProfileEntity());
        assertEquals(source.getOrderDate(), result.getOrderDate());
        assertEquals(addressCustomerEntity, result.getAddressCustomerEntity());
        assertEquals(addressRestaurantEntity, result.getAddressRestaurantEntity());
        assertEquals(source.getTotalPrice(), result.getTotalPrice());
        assertEquals(source.getTransactionDate(), result.getTransactionDate());
        assertNull(result.getCreatedAt());
        assertNull(result.getUpdatedAt());
        assertEquals(orderStatusEntity, result.getOrderStatusEntity());
        assertEquals(orderPaymentEntity, result.getOrderPaymentEntity());
    }
}