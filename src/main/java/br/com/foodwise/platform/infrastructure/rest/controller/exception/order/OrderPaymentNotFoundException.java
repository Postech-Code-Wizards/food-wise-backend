package br.com.foodwise.platform.infrastructure.rest.controller.exception.order;

public class OrderPaymentNotFoundException extends RuntimeException {
    public OrderPaymentNotFoundException(String message) {
        super(message);
    }
}
