package br.com.foodwise.platform.infrastructure.rest.controller.exception.order;

public class OrderStatusNotFoundException extends RuntimeException {
    public OrderStatusNotFoundException(String message) {
        super(message);
    }
}
