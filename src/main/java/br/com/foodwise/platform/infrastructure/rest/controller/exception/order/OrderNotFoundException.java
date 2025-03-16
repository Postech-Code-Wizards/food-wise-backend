package br.com.foodwise.platform.infrastructure.rest.controller.exception.order;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
