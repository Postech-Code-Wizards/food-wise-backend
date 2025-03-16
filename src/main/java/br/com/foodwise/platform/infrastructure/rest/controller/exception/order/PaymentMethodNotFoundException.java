package br.com.foodwise.platform.infrastructure.rest.controller.exception.order;

public class PaymentMethodNotFoundException extends RuntimeException {
    public PaymentMethodNotFoundException(String message) {
        super(message);
    }
}
