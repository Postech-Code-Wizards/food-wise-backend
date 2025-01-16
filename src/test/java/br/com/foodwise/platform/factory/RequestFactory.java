package br.com.foodwise.platform.factory;

import br.com.foodwise.platform.model.entities.*;
import br.com.foodwise.platform.model.entities.enums.PhoneType;
import br.com.foodwise.platform.model.entities.enums.UserType;
import br.com.foodwise.platform.rest.dtos.request.register.AddressRequest;
import br.com.foodwise.platform.rest.dtos.request.register.PhoneRequest;
import br.com.foodwise.platform.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.rest.dtos.request.register.customer.CustomerProfileRequest;
import br.com.foodwise.platform.rest.dtos.request.register.customer.PasswordRequest;
import br.com.foodwise.platform.rest.dtos.request.register.customer.RegisterCustomerRequest;
import br.com.foodwise.platform.rest.dtos.request.register.restaurant.RegisterRestaurantRequest;
import br.com.foodwise.platform.rest.dtos.request.register.restaurant.RestaurantProfileRequest;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class RequestFactory {
    public static RegisterCustomerRequest buildRegisterCustomerRequest(String email, String password) {
        return new RegisterCustomerRequest(new UserRequest(email, password), new CustomerProfileRequest("John", "Doe", buildAddressRequest(), buildPhoneRequest()));
    }


    public static UserRequest buildUserRequest() {
        return new UserRequest("test@code-wizards.com", "password123");
    }

    public static PasswordRequest buildPasswordRequest(){
        return new PasswordRequest("1234567b", "b7654321");
    }

    public static PhoneRequest buildPhoneRequest() {
        return new PhoneRequest("55", "00123456789", PhoneType.MOBILE);
    }

    public static AddressRequest buildAddressRequest() {
        return new AddressRequest("123 Main St", "City", "ST", "Neighborhood", "12345", "Country", BigDecimal.ZERO, BigDecimal.ZERO);
    }

    public static RegisterRestaurantRequest buildValidRegisterRestaurantRequest() {
        var request = new RegisterRestaurantRequest();
        request.setUser(buildUserRequest());
        request.setRestaurant(buildRestaurantProfileRequest());
        return request;
    }

    public static RegisterCustomerRequest buildValidRegisterCustomerRequest() {
        var request = new RegisterCustomerRequest();
        request.setUser(buildUserRequest());
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

    public static User buildUserEntity() {
        var userRequest = buildUserRequest();
        var user = new User();
        user.setId(1L);
        user.setUpdatedAt(null);
        user.setActive(true);
        user.setUserType(UserType.CUSTOMER);
        user.setCreatedAt(ZonedDateTime.now());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        return user;
    }

    public static Address buildAddressEntity() {
        var addressRequest = buildAddressRequest();
        var address = new Address();
        address.setId(1L);
        address.setUpdatedAt(null);
        address.setCity(addressRequest.getCity());
        address.setCountry(addressRequest.getCountry());
        address.setNeighborhood(addressRequest.getNeighborhood());
        address.setPostalCode(addressRequest.getPostalCode());
        address.setStreet(addressRequest.getStreet());
        address.setState(addressRequest.getState());
        address.setLatitude(addressRequest.getLatitude());
        address.setLongitude(addressRequest.getLongitude());
        return address;
    }

    public static Phone buildPhoneEntity() {
        var phoneRequest = buildPhoneRequest();
        var phone = new Phone();
        phone.setId(1L);
        phone.setUpdatedAt(null);
        phone.setCreatedAt(ZonedDateTime.now());
        phone.setAreaCode(phoneRequest.getAreaCode());
        phone.setPhoneNumber(phoneRequest.getPhoneNumber());
        phone.setPhoneType(phoneRequest.getPhoneType());
        return phone;
    }

    public static CustomerProfile buildCustomerProfileEntity() {
        var customerProfileRequest = buildCustomerProfileRequest();
        var customerProfile = new CustomerProfile();
        customerProfile.setUser(buildUserEntity());
        customerProfile.setUpdatedAt(null);
        customerProfile.setCreatedAt(ZonedDateTime.now());
        customerProfile.setFirstName(customerProfileRequest.getFirstName());
        customerProfile.setLastName(customerProfileRequest.getLastName());
        customerProfile.setAddress(buildAddressEntity());
        customerProfile.setPhone(buildPhoneEntity());
        return customerProfile;
    }

    public static RestaurantProfile buildRestaurantProfileEntity() {
        var restaurantProfileRequest = buildRestaurantProfileRequest();
        var restaurantProfile = new RestaurantProfile();
        restaurantProfile.setUser(buildUserEntity());
        restaurantProfile.setPhone(buildPhoneEntity());
        restaurantProfile.setAddress(buildAddressEntity());
        restaurantProfile.setUpdatedAt(null);
        restaurantProfile.setCreatedAt(ZonedDateTime.now());
        restaurantProfile.setBusinessName(restaurantProfileRequest.getBusinessName());
        restaurantProfile.setDescription(restaurantProfileRequest.getDescription());
        restaurantProfile.setCuisineType(restaurantProfileRequest.getCuisineType());
        restaurantProfile.setBusinessHours(restaurantProfileRequest.getBusinessHours());
        restaurantProfile.setDeliveryRadius(restaurantProfileRequest.getDeliveryRadius());
        return restaurantProfile;
    }
}
