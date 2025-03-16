package br.com.foodwise.platform.infrastructure.rest.controller.exception.order;

public class OrderStatusEmptyException extends RuntimeException {
    public OrderStatusEmptyException(String message) {
        super(message);
    }
}
