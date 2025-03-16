package br.com.foodwise.platform.infrastructure.rest.controller.exception;

public class CustomerProfileNotFoundException extends RuntimeException {
    public CustomerProfileNotFoundException(String message) {
        super(message);
    }
}
