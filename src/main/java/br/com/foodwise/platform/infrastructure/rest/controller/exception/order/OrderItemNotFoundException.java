package br.com.foodwise.platform.infrastructure.rest.controller.exception.order;

public class OrderItemNotFoundException extends RuntimeException {
    public OrderItemNotFoundException(String message) {
        super(message);
    }
}
