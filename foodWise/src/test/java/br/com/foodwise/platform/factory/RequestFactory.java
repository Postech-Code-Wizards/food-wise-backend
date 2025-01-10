package br.com.foodwise.platform.factory;

import br.com.foodwise.platform.model.entities.enums.PhoneType;
import br.com.foodwise.platform.rest.dtos.request.register.AddressRequest;
import br.com.foodwise.platform.rest.dtos.request.register.PhoneRequest;
import br.com.foodwise.platform.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.rest.dtos.request.register.customer.CustomerProfileRequest;
import br.com.foodwise.platform.rest.dtos.request.register.customer.RegisterCustomerRequest;
import br.com.foodwise.platform.rest.dtos.request.register.restaurant.RegisterRestaurantRequest;
import br.com.foodwise.platform.rest.dtos.request.register.restaurant.RestaurantProfileRequest;

import java.math.BigDecimal;

public class RequestFactory {
    public static RegisterCustomerRequest buildRegisterCustomerRequest(String email, String password) {
        return new RegisterCustomerRequest(new UserRequest(email, password), new CustomerProfileRequest("John", "Doe", buildAddressRequest(), buildPhoneRequest()));
    }

    public static PhoneRequest buildPhoneRequest() {
        return new PhoneRequest("55", "00123456789", PhoneType.MOBILE);
    }

    public static AddressRequest buildAddressRequest() {
        return new AddressRequest("123 Main St", "City", "ST", "Neighborhood", "12345", "Country", BigDecimal.ZERO, BigDecimal.ZERO);
    }

    public static RegisterRestaurantRequest buildValidRegisterRestaurantRequest() {
        var request = new RegisterRestaurantRequest();
        request.setUser(new UserRequest("test@code-wizards.com", "password123"));
        request.setRestaurant(buildRestaurantProfileRequest());
        return request;
    }

    public static RegisterCustomerRequest buildValidRegisterCustomerRequest() {
        var request = new RegisterCustomerRequest();
        request.setUser(new UserRequest("test@code-wizards.com", "password123"));
        request.setCustomer(buildCustomerProfileRequest());
        return request;
    }

    public static RestaurantProfileRequest buildRestaurantProfileRequest() {
        var restaurant = new RestaurantProfileRequest();
        restaurant.setDeliveryRadius((short) 1);
        restaurant.setBusinessName("Valid Business");
        restaurant.setBusinessHours("09:00-18:00");
        restaurant.setCuisineType("Italian");
        restaurant.setAddress(buildAddressRequest());
        restaurant.setPhone(buildPhoneRequest());
        return restaurant;
    }

    public static CustomerProfileRequest buildCustomerProfileRequest() {
        var request = new CustomerProfileRequest();
        request.setFirstName("Maria");
        request.setLastName("das Flores");
        request.setAddress(buildAddressRequest());
        request.setPhone(buildPhoneRequest());
        return request;
    }
}
