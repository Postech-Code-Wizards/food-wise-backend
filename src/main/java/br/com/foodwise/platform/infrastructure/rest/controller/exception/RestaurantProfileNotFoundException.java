package br.com.foodwise.platform.infrastructure.rest.controller.exception;

public class RestaurantProfileNotFoundException extends RuntimeException {
    public RestaurantProfileNotFoundException(String message) {
        super(message);
    }
}
