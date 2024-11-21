package br.com.foodWise.foodWise.service.exceptions;

public class ResourceNotFoundExceptions extends RuntimeException {
    public ResourceNotFoundExceptions(String message) {
        super(message);
    }
}
