package br.com.foodwise.platform.infrastructure.rest.controller.exception.order;

public class OrderPaymentEmptyException extends RuntimeException {
    public OrderPaymentEmptyException(String message) {
        super(message);
    }
}
