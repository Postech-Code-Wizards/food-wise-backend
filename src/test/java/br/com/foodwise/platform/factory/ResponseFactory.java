package br.com.foodwise.platform.factory;

import br.com.foodwise.platform.infrastructure.rest.dtos.response.CustomerProfileResponse;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.MenuResponse;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;

import static br.com.foodwise.platform.factory.EntityFactory.buildAddress;
import static br.com.foodwise.platform.factory.EntityFactory.buildPhone;

public class ResponseFactory {
//    public static CustomerProfileResponse buildCustomerProfileResponse() {
//        var response = new CustomerProfileResponse();
//        response.setFirstName("Test");
//        response.setLastName("Value");
//        response.setAddressEntity(buildAddress());
//        response.setPhoneEntity(buildPhone());
//        return response;
//    }
//
//    public static RestaurantProfileResponse buildRestaurantProfileResponse() {
//        var response = new RestaurantProfileResponse();
//        response.setBusinessName("Test Restaurant");
//        response.setDescription("");
//        response.setBusinessHours("08:00-17:00");
//        response.setDeliveryRadius((short) 1);
//        response.setCuisineType("Random");
//        response.setOpen(false);
//        response.setAddressEntity(buildAddress());
//        response.setPhoneEntity(buildPhone());
//        return response;
//    }
//
//    public static MenuResponse buildMenuResponse() {
//        var menuResponse = new MenuResponse();
//        menuResponse.setId(0L);
//        menuResponse.setName("Test name");
//        menuResponse.setDescription("Test description");
//        menuResponse.setRestaurant(buildRestaurantProfileResponse());
//        return menuResponse;
//    }
}
