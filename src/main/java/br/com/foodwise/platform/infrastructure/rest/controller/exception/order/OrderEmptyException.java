package br.com.foodwise.platform.infrastructure.rest.controller.exception.order;

public class OrderEmptyException extends RuntimeException {
    public OrderEmptyException(String message) {
        super(message);
    }
}
