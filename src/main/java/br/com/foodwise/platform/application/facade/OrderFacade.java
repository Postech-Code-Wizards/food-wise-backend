package br.com.foodwise.platform.application.facade;

import br.com.foodwise.platform.application.facade.converter.common.AddressRequestToDomainConverter;
import br.com.foodwise.platform.application.facade.converter.order.*;
import br.com.foodwise.platform.application.usecase.customer.RetrieveCustomerProfileByIdUseCase;
import br.com.foodwise.platform.application.usecase.menuItem.RetrieveAllMenuItemsByIdsUseCase;
import br.com.foodwise.platform.application.usecase.order.*;
import br.com.foodwise.platform.application.usecase.order.orderitems.CreateOrderItemsUseCase;
import br.com.foodwise.platform.application.usecase.order.orderpayment.CreateOrderPaymentUseCase;
import br.com.foodwise.platform.application.usecase.order.orderstatus.CreateOrderStatusUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.RetrieveRestaurantProfileByIdUseCase;
import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.domain.OrderItem;
import br.com.foodwise.platform.domain.enums.OrderStage;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.order.OrderItemEmptyException;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.order.OrderPaymentEmptyException;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.AddressRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.order.CreateOrderRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.order.OrderItemsRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.order.OrderPaymentRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.order.UpdateOrderTotalPriceRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.orders.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final CreateOrderUseCase createOrderUseCase;
    private final CreateOrderStatusUseCase createOrderStatusUseCase;
    private final CreateOrderItemsUseCase createOrderItemsUseCase;
    private final CreateOrderPaymentUseCase createOrderPaymentUseCase;
    private final ListOrdersUseCase listOrdersUseCase;
    private final RetrieveOrderUseCase retrieveOrderUseCase;
    private final RetrieveAllMenuItemsByIdsUseCase retrieveAllMenuItemsByIdsUseCase;
    private final UpdateOrderCustomerAddressUseCase updateOrderCustomerAddressUseCase;
    private final UpdateOrderRestaurantAddressUseCase updateOrderRestaurantAddressUseCase;
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
    private final OrderItemRequestToOrderItemConverter toOrderItemConverter;
    private final OrderPaymentRequestToOrderPaymentConverter toOrderPaymentConverter;
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

    public void updateOrderCustomerAddress(Long id, AddressRequest request) {

        var address = toAddressEntityConverter.convert(request);
        updateOrderCustomerAddressUseCase.updateOrder(id, address);
    }

    public void updateOrderRestaurantAddress(Long id, AddressRequest request) {
        var address = toAddressEntityConverter.convert(request);
        updateOrderRestaurantAddressUseCase.updateOrder(id, address);
    }

    public void updateOrderOrderItems(Long id, OrderItemsRequest request) {
        List<OrderItem> orderItem = Optional.ofNullable(toOrderItemConverter.convert(request))
                .orElseThrow(() -> new OrderItemEmptyException("Order item is empty"));
        updateOrderOrderItemsUseCase.updateOrderItems(id, orderItem);
    }

    public void updateOrderOrderPayment(Long id, OrderPaymentRequest request) {
        var orderPayment = toOrderPaymentConverter.convert(request);
        if (!ObjectUtils.isEmpty(orderPayment)) {
            updateOrderOrderPaymentUseCase.updateOrder(id, orderPayment);
        } else {
            throw new OrderPaymentEmptyException("Order payment is empty");
        }
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
