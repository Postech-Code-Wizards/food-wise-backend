package br.com.foodwise.platform.infrastructure.rest.controller;

import br.com.foodwise.platform.application.facade.OrderFacade;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.AddressRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.order.CreateOrderRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.update.order.UpdateOrderItemsRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.update.order.UpdateOrderPaymentRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.update.order.UpdateOrderTotalPriceRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.orders.OrderResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order")
public class OrderController implements OrderApi {
    private final OrderFacade orderFacade;

    @Override
    public ResponseEntity<Void> createOrder(@RequestBody @Valid CreateOrderRequest request) {
        orderFacade.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<List<OrderResponse>> listOrders() {
        var orders = orderFacade.listOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @Override
    public ResponseEntity<OrderResponse> retrieveOrder(
            @Parameter(description = "ID of the order to retrieve", required = true) @PathVariable Long id
    ) {
        var order = orderFacade.retrieveOrder(id);
        return ResponseEntity.ok(order);
    }

    @Override
    public ResponseEntity<Void> updateOrderCustomerAddress(
            @Parameter(description = "ID of the order to update", required = true) @PathVariable Long id,
            @RequestBody @Valid AddressRequest request
    ) {
        orderFacade.updateOrderCustomerAddress(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> updateOrderItems(
            @Parameter(description = "ID of the order to update", required = true) @PathVariable Long id,
            @RequestBody @Valid UpdateOrderItemsRequest request
    ) {
        orderFacade.updateOrderItems(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> updateOrderPayment(
            @Parameter(description = "ID of the order to update", required = true) @PathVariable Long id,
            @RequestBody @Valid UpdateOrderPaymentRequest request
    ) {
        orderFacade.updateOrderPayment(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> updateOrderTotalPrice(
            @Parameter(description = "ID of the order to update", required = true) @PathVariable Long id,
            @RequestBody @Valid UpdateOrderTotalPriceRequest request
    ) {
        orderFacade.updateOrderTotalPrice(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> cancelOrder(
            @Parameter(description = "ID of the order to cancel", required = true) @PathVariable Long id
    ) {
        orderFacade.cancelOrder(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
