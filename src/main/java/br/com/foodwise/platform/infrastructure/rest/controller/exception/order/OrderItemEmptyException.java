package br.com.foodwise.platform.infrastructure.rest.controller.exception.order;

public class OrderItemEmptyException extends RuntimeException {
    public OrderItemEmptyException(String message) {
        super(message);
    }
}
