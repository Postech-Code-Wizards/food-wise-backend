package br.com.foodwise.platform.application.facade;

import br.com.foodwise.platform.application.facade.converter.common.AddressRequestToDomainConverter;
import br.com.foodwise.platform.application.facade.converter.order.CreateOrderRequestToOrderConverter;
import br.com.foodwise.platform.application.facade.converter.order.OrderToOrderResponseConverter;
import br.com.foodwise.platform.application.facade.converter.order.OrdersToOrdersResponsesConverter;
import br.com.foodwise.platform.application.usecase.customer.RetrieveCustomerProfileByIdUseCase;
import br.com.foodwise.platform.application.usecase.menuItem.RetrieveAllMenuItemsByIdsUseCase;
import br.com.foodwise.platform.application.usecase.order.*;
import br.com.foodwise.platform.application.usecase.order.orderitems.CreateOrderItemsUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.RetrieveRestaurantProfileByIdUseCase;
import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.AddressRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.order.CreateOrderRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.update.order.UpdateOrderItemsRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.update.order.UpdateOrderPaymentRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.update.order.UpdateOrderTotalPriceRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.orders.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final CreateOrderUseCase createOrderUseCase;
    private final CreateOrderItemsUseCase createOrderItemsUseCase;
    private final ListOrdersUseCase listOrdersUseCase;
    private final RetrieveOrderUseCase retrieveOrderUseCase;
    private final RetrieveAllMenuItemsByIdsUseCase retrieveAllMenuItemsByIdsUseCase;
    private final UpdateOrderCustomerAddressUseCase updateOrderCustomerAddressUseCase;
    private final UpdateOrderOrderItemsUseCase updateOrderOrderItemsUseCase;
    private final UpdateOrderOrderPaymentUseCase updateOrderOrderPaymentUseCase;
    private final UpdateOrderTotalPriceUseCase updateOrderTotalPriceUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;
    private final CancelOrderStatusUseCase cancelOrderStatusUseCase;
    private final CancelOrderPaymentUseCase cancelOrderPaymentUseCase;
    private final RetrieveCustomerProfileByIdUseCase retrieveCustomerProfileByIdUseCase;
    private final RetrieveRestaurantProfileByIdUseCase retrieveRestaurantProfileByIdUseCase;

    private final CreateOrderRequestToOrderConverter toOrderConverter;
    private final OrdersToOrdersResponsesConverter toOrdersResponsesConverter;
    private final OrderToOrderResponseConverter toOrderResponseConverter;
    private final AddressRequestToDomainConverter toAddressEntityConverter;

    public void createOrder(CreateOrderRequest request) {
        var customerProfile = retrieveCustomerProfileByIdUseCase.retrieveById(request.getCustomerProfileId());
        var restaurantProfile = retrieveRestaurantProfileByIdUseCase.retrieveById(request.getRestaurantProfileId());
        var menuItems = retrieveAllMenuItemsByIdsUseCase.retrieveAllById(request.getMenuItemsIds());

        var order = toOrderConverter.convert(request, customerProfile, restaurantProfile);
        var orderSaved = createOrderUseCase.create(order);
        createOrderItemsUseCase.create(menuItems, orderSaved);
    }

    public List<OrderResponse> listOrders() {

        return toOrdersResponsesConverter.convert(listOrdersUseCase.listOrders());
    }

    public OrderResponse retrieveOrder(Long id) {
        return toOrderResponseConverter.convert(retrieveOrderUseCase.retrieveOrder(id));
    }

    public void updateOrderCustomerAddress(Long orderId, AddressRequest request) {
        var order = retrieveOrderUseCase.retrieveOrder(orderId);
        var address = toAddressEntityConverter.convert(request);
        updateOrderCustomerAddressUseCase.updateOrder(order, address);
    }

    public void updateOrderItems(Long orderId, UpdateOrderItemsRequest request) {
        var order = retrieveOrderUseCase.retrieveOrder(orderId);
        var menuItems = retrieveAllMenuItemsByIdsUseCase.retrieveAllById(request.getMenuItemsIds());
        updateOrderOrderItemsUseCase.updateOrderItems(order, menuItems);
    }

    public void updateOrderPayment(Long id, UpdateOrderPaymentRequest request) {
        updateOrderOrderPaymentUseCase.updateOrder(id, request);
    }

    public void updateOrderTotalPrice(Long id, UpdateOrderTotalPriceRequest request) {
        updateOrderTotalPriceUseCase.updateOrder(id, request);
    }

    public void cancelOrder(Long orderId) {
        Order order = retrieveOrderUseCase.retrieveOrder(orderId);
        cancelOrderStatusUseCase.cancelOrderStatus(order);
        cancelOrderPaymentUseCase.cancelOrderPayment(order);
        cancelOrderUseCase.cancelOrder(order);
    }
}
