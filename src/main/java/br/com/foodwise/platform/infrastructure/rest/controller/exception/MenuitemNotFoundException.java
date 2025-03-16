package br.com.foodwise.platform.infrastructure.rest.controller.exception;

public class MenuitemNotFoundException extends RuntimeException {
    public MenuitemNotFoundException(String message) {
        super(message);
    }
}
