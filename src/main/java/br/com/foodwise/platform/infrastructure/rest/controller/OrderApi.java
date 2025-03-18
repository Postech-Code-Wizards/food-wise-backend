package br.com.foodwise.platform.infrastructure.rest.controller;

import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.AddressRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.order.CreateOrderRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.order.OrderItemsRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.order.OrderPaymentRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.order.UpdateOrderTotalPriceRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.orders.OrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface OrderApi {
    @Operation(summary = "Create a new orderEntity", description = "Create a new orderEntity in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    """
                                            {
                                                "statusCode": 404,
                                                "errors": [
                                                    {
                                                        "code": "ORDER_NOT_FOUND",
                                                        "message": "Order not found"
                                                    }
                                                ]
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    """
                                            {
                                                "statusCode": 500,
                                                "errors": [
                                                    {
                                                        "code": "INTERNAL_SERVER_ERROR",
                                                        "message": "Internal server error"
                                                    }
                                                ]
                                            }
                                            """
                            )
                    )
            )
    })
    @PostMapping("/register")
    ResponseEntity<Void> createOrder(@RequestBody @Valid CreateOrderRequest request);

    @Operation(summary = "List all orders", description = "Retrieve a list of all orders.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of orders retrieved successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    """
                                            {
                                                "statusCode": 500,
                                                "errors": [
                                                    {
                                                        "code": "INTERNAL_SERVER_ERROR",
                                                        "message": "Internal server error"
                                                    }
                                                ]
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping
    ResponseEntity<List<OrderResponse>> listOrders();

    @Operation(summary = "Retrieve an orderEntity", description = "Retrieve details of an orderEntity by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order retrieved successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    """
                                            {
                                                "statusCode": 404,
                                                "errors": [
                                                    {
                                                        "code": "ORDER_NOT_FOUND",
                                                        "message": "Order not found"
                                                    }
                                                ]
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping("/{id}")
    ResponseEntity<OrderResponse> retrieveOrder(
            @Parameter(description = "ID of the orderEntity to retrieve", required = true) @PathVariable Long id
    );

    @Operation(summary = "Update customer's address", description = "Update the address of the customer for the given orderEntity.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer address updated successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    """
                                            {
                                                "statusCode": 404,
                                                "errors": [
                                                    {
                                                        "code": "ORDER_NOT_FOUND",
                                                        "message": "Order not found"
                                                    }
                                                ]
                                            }
                                            """
                            )
                    )
            )
    })
    @PutMapping("/{id}/customer-address")
    ResponseEntity<Void> updateOrderCustomerAddress(
            @Parameter(description = "ID of the orderEntity to update", required = true) @PathVariable Long id,
            @RequestBody @Valid AddressRequest request
    );

    @Operation(summary = "Update restaurant's address", description = "Update the address of the restaurant for the given orderEntity.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant address updated successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    """
                                            {
                                                "statusCode": 404,
                                                "errors": [
                                                    {
                                                        "code": "ORDER_NOT_FOUND",
                                                        "message": "Order not found"
                                                    }
                                                ]
                                            }
                                            """
                            )
                    )
            )
    })
    @PatchMapping("/{id}/restaurant-address")
    ResponseEntity<Void> updateOrderRestaurantAddress(
            @Parameter(description = "ID of the orderEntity to update", required = true) @PathVariable Long id,
            @RequestBody @Valid AddressRequest request
    );

    @Operation(summary = "Update orderEntity items", description = "Update the items of the given orderEntity.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order items updated successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    """
                                            {
                                                "statusCode": 404,
                                                "errors": [
                                                    {
                                                        "code": "ORDER_NOT_FOUND",
                                                        "message": "Order not found"
                                                    }
                                                ]
                                            }
                                            """
                            )
                    )
            )
    })
    @PatchMapping("/{id}/orderEntity-items")
    ResponseEntity<Void> updateOrderItems(
            @Parameter(description = "ID of the orderEntity to update", required = true) @PathVariable Long id,
            @RequestBody @Valid OrderItemsRequest request
    );

    @Operation(summary = "Update orderEntity payment", description = "Update the payment method for the given orderEntity.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order payment updated successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    """
                                            {
                                                "statusCode": 404,
                                                "errors": [
                                                    {
                                                        "code": "ORDER_NOT_FOUND",
                                                        "message": "Order not found"
                                                    }
                                                ]
                                            }
                                            """
                            )
                    )
            )
    })
    @PatchMapping("/{id}/orderEntity-payment")
    ResponseEntity<Void> updateOrderPayment(
            @Parameter(description = "ID of the orderEntity to update", required = true) @PathVariable Long id,
            @RequestBody @Valid OrderPaymentRequest request
    );

    @Operation(summary = "Update total price of the orderEntity", description = "Update the total price of the orderEntity.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order total price updated successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    """
                                            {
                                                "statusCode": 404,
                                                "errors": [
                                                    {
                                                        "code": "ORDER_NOT_FOUND",
                                                        "message": "Order not found"
                                                    }
                                                ]
                                            }
                                            """
                            )
                    )
            )
    })
    @PatchMapping("/{id}/total-price")
    ResponseEntity<Void> updateOrderTotalPrice(
            @Parameter(description = "ID of the orderEntity to update", required = true) @PathVariable Long id,
            @RequestBody @Valid UpdateOrderTotalPriceRequest request
    );

    @Operation(summary = "Cancel an orderEntity", description = "Cancel an orderEntity by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Order canceled successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    """
                                            {
                                                "statusCode": 404,
                                                "errors": [
                                                    {
                                                        "code": "ORDER_NOT_FOUND",
                                                        "message": "Order not found"
                                                    }
                                                ]
                                            }
                                            """
                            )
                    )
            )
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> cancelOrder(
            @Parameter(description = "ID of the orderEntity to cancel", required = true) @PathVariable Long id
    );
}
