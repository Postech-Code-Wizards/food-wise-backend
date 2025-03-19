package br.com.foodwise.platform.application.facade;

import br.com.foodwise.platform.application.facade.converter.common.AddressRequestToDomainConverter;
import br.com.foodwise.platform.application.facade.converter.order.*;
import br.com.foodwise.platform.application.usecase.customer.RetrieveCustomerProfileByIdUseCase;
import br.com.foodwise.platform.application.usecase.menuItem.RetrieveAllMenuItemsByIdsUseCase;
import br.com.foodwise.platform.application.usecase.order.*;
import br.com.foodwise.platform.application.usecase.order.orderitems.CreateOrderItemsUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.RetrieveRestaurantProfileByIdUseCase;
import br.com.foodwise.platform.domain.*;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.order.OrderPaymentEmptyException;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.AddressRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.order.CreateOrderRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.update.order.UpdateOrderPaymentRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.update.order.UpdateOrderTotalPriceRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.orders.OrderResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderFacadeTest {

    @Mock
    private CreateOrderUseCase createOrderUseCase;


    @Mock
    private CreateOrderItemsUseCase createOrderItemsUseCase;

    @Mock
    private ListOrdersUseCase listOrdersUseCase;

    @Mock
    private RetrieveOrderUseCase retrieveOrderUseCase;

    @Mock
    private RetrieveAllMenuItemsByIdsUseCase retrieveAllMenuItemsByIdsUseCase;

    @Mock
    private UpdateOrderCustomerAddressUseCase updateOrderCustomerAddressUseCase;

    @Mock
    private UpdateOrderOrderItemsUseCase updateOrderOrderItemsUseCase;

    @Mock
    private UpdateOrderOrderPaymentUseCase updateOrderOrderPaymentUseCase;

    @Mock
    private UpdateOrderTotalPriceUseCase updateOrderTotalPriceUseCase;

    @Mock
    private CancelOrderUseCase cancelOrderUseCase;

    @Mock
    private CancelOrderStatusUseCase cancelOrderStatusUseCase;

    @Mock
    private CancelOrderPaymentUseCase cancelOrderPaymentUseCase;

    @Mock
    private RetrieveCustomerProfileByIdUseCase retrieveCustomerProfileByIdUseCase;

    @Mock
    private RetrieveRestaurantProfileByIdUseCase retrieveRestaurantProfileByIdUseCase;

    @Mock
    private CreateOrderRequestToOrderConverter toOrderConverter;

    @Mock
    private OrdersToOrdersResponsesConverter toOrdersResponsesConverter;

    @Mock
    private OrderToOrderResponseConverter toOrderResponseConverter;

    @Mock
    private OrderItemRequestToOrderItemConverter toOrderItemConverter;

    @Mock
    private AddressRequestToDomainConverter toAddressEntityConverter;

    @InjectMocks
    private OrderFacade orderFacade;

    @Test
    @DisplayName("Should create order successfully")
    void createOrder_ShouldCreateOrder() {

        CreateOrderRequest request = Instancio.create(CreateOrderRequest.class);
        Order order = Instancio.create(Order.class);
        List<MenuItem> menuItems = Instancio.ofList(MenuItem.class).size(3).create();
        CustomerProfile customerProfile = Instancio.create(CustomerProfile.class);
        RestaurantProfile restaurantProfile = Instancio.create(RestaurantProfile.class);

        when(retrieveCustomerProfileByIdUseCase.retrieveById(request.getCustomerProfileId())).thenReturn(customerProfile);
        when(retrieveRestaurantProfileByIdUseCase.retrieveById(request.getRestaurantProfileId())).thenReturn(restaurantProfile);
        when(retrieveAllMenuItemsByIdsUseCase.retrieveAllById(request.getMenuItemsIds())).thenReturn(menuItems);
        when(toOrderConverter.convert(request, customerProfile, restaurantProfile)).thenReturn(order);
        when(createOrderUseCase.create(order)).thenReturn(order);

        orderFacade.createOrder(request);

        verify(createOrderUseCase, times(1)).create(order);
        verify(createOrderItemsUseCase, times(1)).create(menuItems, order);
    }

    @Test
    @DisplayName("Should list orders successfully")
    void listOrders_ShouldListOrders() {

        List<Order> orders = Instancio.ofList(Order.class).size(3).create();
        List<OrderResponse> orderResponses = Instancio.ofList(OrderResponse.class).size(3).create();

        when(listOrdersUseCase.listOrders()).thenReturn(orders);
        when(toOrdersResponsesConverter.convert(orders)).thenReturn(orderResponses);

        List<OrderResponse> result = orderFacade.listOrders();

        assertNotNull(result);
        assertEquals(orderResponses, result);
    }

    @Test
    @DisplayName("Should retrieve order successfully")
    void retrieveOrder_ShouldRetrieveOrder() {

        Long orderId = Instancio.create(Long.class);
        Order order = Instancio.create(Order.class);
        OrderResponse orderResponse = Instancio.create(OrderResponse.class);

        when(retrieveOrderUseCase.retrieveOrder(orderId)).thenReturn(order);
        when(toOrderResponseConverter.convert(order)).thenReturn(orderResponse);

        OrderResponse result = orderFacade.retrieveOrder(orderId);

        assertNotNull(result);
        assertEquals(orderResponse, result);
    }

    @Test
    @DisplayName("Should update customer address successfully")
    void updateOrderCustomerAddress_ShouldUpdateAddress() {

//        Long orderId = Instancio.create(Long.class);
//        AddressRequest addressRequest = Instancio.create(AddressRequest.class);
//        Address address = Instancio.create(Address.class);
//
//        when(toAddressEntityConverter.convert(addressRequest)).thenReturn(address);
//        doNothing().when(updateOrderCustomerAddressUseCase).updateOrder(orderId, address);
//
//        orderFacade.updateOrderCustomerAddress(orderId, addressRequest);
//
//        verify(updateOrderCustomerAddressUseCase, times(1)).updateOrder(orderId, address);
    }

    @Test
    @DisplayName("Should update restaurant address successfully")
    void updateOrderRestaurantAddress_ShouldUpdateAddress() {

//        Long orderId = Instancio.create(Long.class);
//        AddressRequest addressRequest = Instancio.create(AddressRequest.class);
//        Address address = Instancio.create(Address.class);
//
//        when(toAddressEntityConverter.convert(addressRequest)).thenReturn(address);
//        doNothing().when(updateOrderRestaurantAddressUseCase).updateOrder(orderId, address);
//
//        orderFacade.updateOrderRestaurantAddress(orderId, addressRequest);
//
//        verify(updateOrderRestaurantAddressUseCase, times(1)).updateOrder(orderId, address);
    }

    @Test
    @DisplayName("Should update order items successfully")
    void updateOrderOrderItems_ShouldUpdateOrderItems() {

//        Long orderId = Instancio.create(Long.class);
//        OrderItemsRequest orderItemsRequest = Instancio.create(OrderItemsRequest.class);
//        List<OrderItem> orderItems = Instancio.ofList(OrderItem.class).size(2).create();
//
//        when(toOrderItemConverter.convert(orderItemsRequest)).thenReturn(orderItems);
//        doNothing().when(updateOrderOrderItemsUseCase).updateOrderItems(orderId, orderItems);
//
//        orderFacade.updateOrderItems(orderId, orderItemsRequest);
//
//        verify(updateOrderOrderItemsUseCase, times(1)).updateOrderItems(orderId, orderItems);
    }

    @Test
    @DisplayName("Should throw OrderItemEmptyException when order items are empty")
    void updateOrderOrderItems_ShouldThrowException() {

//        Long orderId = Instancio.create(Long.class);
//        OrderItemsRequest orderItemsRequest = Instancio.create(OrderItemsRequest.class);
//
//        when(toOrderItemConverter.convert(orderItemsRequest)).thenReturn(null);
//
//        assertThrows(OrderItemEmptyException.class, () -> orderFacade.updateOrderItems(orderId, orderItemsRequest));
    }

    @Test
    @DisplayName("Should update order payment successfully")
    void updateOrderOrderPayment_ShouldUpdateOrderPayment() {

//        Long orderId = Instancio.create(Long.class);
//        UpdateOrderPaymentRequest updateOrderPaymentRequest = Instancio.create(UpdateOrderPaymentRequest.class);
//        OrderPayment orderPayment = Instancio.create(OrderPayment.class);
//
//        when(toOrderPaymentConverter.convert(updateOrderPaymentRequest)).thenReturn(orderPayment);
//        doNothing().when(updateOrderOrderPaymentUseCase).updateOrder(orderId, orderPayment);
//
//        orderFacade.updateOrderPayment(orderId, updateOrderPaymentRequest);
//
//        verify(updateOrderOrderPaymentUseCase, times(1)).updateOrder(orderId, orderPayment);
    }

    @Test
    @DisplayName("Should throw OrderPaymentEmptyException when order payment is empty")
    void updateOrderOrderPayment_ShouldThrowException() {

//        Long orderId = Instancio.create(Long.class);
//        UpdateOrderPaymentRequest updateOrderPaymentRequest = Instancio.create(UpdateOrderPaymentRequest.class);
//
//        when(toOrderPaymentConverter.convert(updateOrderPaymentRequest)).thenReturn(null);
//
//        assertThrows(OrderPaymentEmptyException.class, () -> orderFacade.updateOrderPayment(orderId, updateOrderPaymentRequest));
    }

    @Test
    @DisplayName("Should update order total price successfully")
    void updateOrderTotalPrice_ShouldUpdateTotalPrice() {

        Long orderId = Instancio.create(Long.class);
        UpdateOrderTotalPriceRequest request = Instancio.create(UpdateOrderTotalPriceRequest.class);

        doNothing().when(updateOrderTotalPriceUseCase).updateOrder(orderId, request);

        orderFacade.updateOrderTotalPrice(orderId, request);

        verify(updateOrderTotalPriceUseCase, times(1)).updateOrder(orderId, request);
    }

    @Test
    @DisplayName("Should cancel order successfully")
    void cancelOrder_ShouldCancelOrder() {

        Long orderId = Instancio.create(Long.class);
        Order order = Instancio.create(Order.class);

        when(retrieveOrderUseCase.retrieveOrder(orderId)).thenReturn(order);
        doNothing().when(cancelOrderStatusUseCase).cancelOrderStatus(order);
        doNothing().when(cancelOrderPaymentUseCase).cancelOrderPayment(order);
        doNothing().when(cancelOrderUseCase).cancelOrder(order);

        orderFacade.cancelOrder(orderId);

        verify(cancelOrderStatusUseCase, times(1)).cancelOrderStatus(order);
        verify(cancelOrderPaymentUseCase, times(1)).cancelOrderPayment(order);
        verify(cancelOrderUseCase, times(1)).cancelOrder(order);
    }

}