package br.com.foodwise.factory;

import br.com.foodwise.model.entities.enums.PhoneType;
import br.com.foodwise.model.entities.enums.UserType;
import br.com.foodwise.rest.dtos.request.register.AddressRequest;
import br.com.foodwise.rest.dtos.request.register.PhoneRequest;
import br.com.foodwise.rest.dtos.request.register.UserRequest;
import br.com.foodwise.rest.dtos.request.register.customer.CustomerProfileRequest;
import br.com.foodwise.rest.dtos.request.register.customer.RegisterCustomerRequest;
import br.com.foodwise.rest.dtos.request.register.restaurant.RegisterRestaurantRequest;
import br.com.foodwise.rest.dtos.request.register.restaurant.RestaurantProfileRequest;

public class RequestFactory {
    public static RegisterCustomerRequest buildRegisterCustomerRequest(String email, String password) {
        return new RegisterCustomerRequest(new UserRequest(email, password, UserType.CUSTOMER), new CustomerProfileRequest("John", "Doe", buildAddressRequest(), buildPhoneRequest()));
    }

    private static PhoneRequest buildPhoneRequest() {
        return new PhoneRequest("555", "1234567890", PhoneType.MOBILE);
    }

    private static AddressRequest buildAddressRequest() {
        return new AddressRequest("123 Main St", "City", "State", "Neighborhood", "12345", "Country", null, null);
    }

    public static RegisterRestaurantRequest buildValidRegisterRestaurantRequest() {
        var request = new RegisterRestaurantRequest();
        request.setUser(new UserRequest("test@example.com", "password123", UserType.RESTAURANT_OWNER));
        request.setRestaurant(buildValidRestaurantRequest());
        return request;
    }

    public static RegisterCustomerRequest buildValidRegisterCustomerRequest() {
        var request = new RegisterCustomerRequest();
        request.setUser(new UserRequest("test@example.com", "password123", UserType.RESTAURANT_OWNER));
        request.setCustomer(buildValidCustomerRequest());
        return request;
    }

    public static RestaurantProfileRequest buildValidRestaurantRequest() {
        var restaurant = new RestaurantProfileRequest();
        restaurant.setDeliveryRadius((short) 1);
        restaurant.setBusinessName("Valid Business");
        restaurant.setBusinessHours("09:00-18:00");
        restaurant.setCuisineType("Italian");
        restaurant.setAddress(buildAddressRequest());
        restaurant.setPhone(buildPhoneRequest());
        return restaurant;
    }

    public static CustomerProfileRequest buildValidCustomerRequest() {
        var request = new CustomerProfileRequest();
        request.setFirstName("Maria");
        request.setLastName("das Flores");
        request.setAddress(buildAddressRequest());
        request.setPhone(buildPhoneRequest());
        return request;
    }
}
