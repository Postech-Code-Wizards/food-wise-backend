package br.com.foodwise.platform.gateway.database.jpa.converter.orders;

import br.com.foodwise.platform.domain.*;
import br.com.foodwise.platform.gateway.database.jpa.converter.AddressEntityToDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.CustomerProfileEntityToDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.RestaurantProfileEntityToDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.orderpayment.OrderPaymentEntityToOrderPaymentConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.orderstatus.OrderStatusEntityToOrderStatusDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.OrderEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderEntityToOrderDomainConverterTest {

    @Mock
    private AddressEntityToDomainConverter addressEntityToDomainConverter;

    @Mock
    private OrderStatusEntityToOrderStatusDomainConverter orderStatusEntityToOrderStatusDomainConverter;

    @Mock
    private OrderPaymentEntityToOrderPaymentConverter orderPaymentEntityToOrderPaymentConverter;

    @Mock
    private CustomerProfileEntityToDomainConverter customerProfileEntityToDomainConverter;

    @Mock
    private RestaurantProfileEntityToDomainConverter restaurantProfileEntityToDomainConverter;

    @InjectMocks
    private OrderEntityToOrderDomainConverter converter;

    @Test
    @DisplayName("Should convert OrderEntity to Order domain")
    void convert_ShouldConvertOrderEntityToOrder() {

        OrderEntity source = Instancio.create(OrderEntity.class);
        CustomerProfile customerProfile = Instancio.create(CustomerProfile.class);
        RestaurantProfile restaurantProfile = Instancio.create(RestaurantProfile.class);
        Address addressCustomer = Instancio.create(Address.class);
        Address addressRestaurant = Instancio.create(Address.class);
        OrderStatus orderStatus = Instancio.create(OrderStatus.class);
        OrderPayment orderPayment = Instancio.create(OrderPayment.class);

        when(customerProfileEntityToDomainConverter.convert(source.getCustomerProfileEntity())).thenReturn(customerProfile);
        when(restaurantProfileEntityToDomainConverter.convert(source.getRestaurantProfileEntity())).thenReturn(restaurantProfile);
        when(addressEntityToDomainConverter.convert(source.getAddressCustomerEntity())).thenReturn(addressCustomer);
        when(addressEntityToDomainConverter.convert(source.getAddressRestaurantEntity())).thenReturn(addressRestaurant);
        when(orderStatusEntityToOrderStatusDomainConverter.convert(source.getOrderStatusEntity())).thenReturn(orderStatus);
        when(orderPaymentEntityToOrderPaymentConverter.convert(source.getOrderPaymentEntity())).thenReturn(orderPayment);

        Order result = converter.convert(source);

        assertEquals(source.getId(), result.getId());
        assertEquals(customerProfile, result.getCustomerProfile());
        assertEquals(restaurantProfile, result.getRestaurantProfile());
        assertEquals(addressCustomer, result.getAddressCustomer());
        assertEquals(addressRestaurant, result.getAddressRestaurant());
        assertEquals(source.getOrderDate(), result.getOrderDate());
        assertEquals(orderStatus, result.getOrderStatus());
        assertEquals(orderPayment, result.getOrderPayment());
        assertEquals(source.getCreatedAt(), result.getCreatedAt());
        assertEquals(source.getUpdatedAt(), result.getUpdatedAt());
        assertEquals(source.getTotalPrice(), result.getTotalPrice());
        assertEquals(source.getTransactionDate(), result.getTransactionDate());
    }

}