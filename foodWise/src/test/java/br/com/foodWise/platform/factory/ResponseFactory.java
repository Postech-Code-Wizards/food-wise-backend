package br.com.foodwise.platform.factory;

import br.com.foodwise.platform.rest.dtos.response.CustomerProfileResponse;
import br.com.foodwise.platform.rest.dtos.response.RestaurantProfileResponse;

import static br.com.foodwise.platform.factory.EntityFactory.buildAddress;
import static br.com.foodwise.platform.factory.EntityFactory.buildPhone;

public class ResponseFactory {
    public static CustomerProfileResponse buildCustomerProfileResponse() {
        var response = new CustomerProfileResponse();
        response.setFirstName("Test");
        response.setLastName("Value");
        response.setAddress(buildAddress());
        response.setPhone(buildPhone());
        return response;
    }

    public static RestaurantProfileResponse buildRestaurantProfileResponse() {
        var response = new RestaurantProfileResponse();
        response.setBusinessName("Test Restaurant");
        response.setDescription("");
        response.setBusinessHours("08:00-17:00");
        response.setDeliveryRadius((short) 1);
        response.setCuisineType("Random");
        response.setOpen(false);
        response.setAddress(buildAddress());
        response.setPhone(buildPhone());
        return response;
    }
}
