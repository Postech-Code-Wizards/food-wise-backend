package br.com.foodwise.platform.factory;

import br.com.foodwise.platform.domain.enums.PhoneType;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.gateway.database.jpa.entities.AddressEntity;
import br.com.foodwise.platform.gateway.database.jpa.entities.CustomerProfileEntity;
import br.com.foodwise.platform.gateway.database.jpa.entities.PhoneEntity;
import br.com.foodwise.platform.gateway.database.jpa.entities.RestaurantProfileEntity;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.AddressRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.PhoneRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.CustomerProfileRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.RegisterCustomerRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menu.RegisterMenuRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RegisterRestaurantOwnerRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RegisterRestaurantRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RestaurantProfileRequest;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class RequestFactory {
    public static RegisterCustomerRequest buildRegisterCustomerRequest(String email, String password) {
        return new RegisterCustomerRequest(new UserRequest(email, password), new CustomerProfileRequest("John", "Doe", buildAddressRequest(), buildPhoneRequest()));
    }


    public static UserRequest buildUserRequest() {
        return new UserRequest("test@code-wizards.com", "password123");
    }

    public static PhoneRequest buildPhoneRequest() {
        return new PhoneRequest(1L, "55", "00123456789", PhoneType.MOBILE);
    }

    public static AddressRequest buildAddressRequest() {
        return new AddressRequest(1L, "123 Main St", "City", "ST", "Neighborhood", "12345", "Country", BigDecimal.ZERO, BigDecimal.ZERO);
    }

    public static RegisterRestaurantRequest buildValidRegisterRestaurantRequest() {
        var request = new RegisterRestaurantRequest();
        request.setUser(buildUserRequest());
        request.setRestaurant(buildRestaurantProfileRequest());
        request.setOwner(buildrestaurantOwnerRequest());
        return request;
    }

    public static RegisterCustomerRequest buildValidRegisterCustomerRequest() {
        var request = new RegisterCustomerRequest();
        request.setUser(buildUserRequest());
        request.setCustomer(buildCustomerProfileRequest());
        return request;
    }

    public static RegisterMenuRequest buildRegisterMenuRequest() {
        var menuRequest = new RegisterMenuRequest();
        menuRequest.setName("Request Test");
        menuRequest.setDescription("Test Description");
        return menuRequest;
    }

    public static RegisterRestaurantOwnerRequest buildrestaurantOwnerRequest() {
        var owner = new RegisterRestaurantOwnerRequest();
        owner.setFirstName("John");
        owner.setLastName("Doe");
        owner.setBusinessRegistrationNumber("38546898022");
        owner.setBusinessEmail("john@doe.com");
        return owner;
    }

    public static RestaurantProfileRequest buildRestaurantProfileRequest() {
        var restaurant = new RestaurantProfileRequest();
        restaurant.setDeliveryRadius((short) 1);
        restaurant.setBusinessName("Valid Business");
        restaurant.setBusinessHours("09:00-18:00");
        restaurant.setCuisineType("Italian");
        restaurant.setIsDeliveryOrder(true);
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

    public static UserEntity buildUserEntity() {
        var userRequest = buildUserRequest();
        var user = new UserEntity();
        user.setId(1L);
        user.setUpdatedAt(null);
        user.setActive(true);
        user.setUserType(UserType.CUSTOMER);
        user.setCreatedAt(ZonedDateTime.now());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        return user;
    }

    public static AddressEntity buildAddressEntity() {
        var addressRequest = buildAddressRequest();
        var address = new AddressEntity();
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

    public static PhoneEntity buildPhoneEntity() {
        var phoneRequest = buildPhoneRequest();
        var phone = new PhoneEntity();
        phone.setId(1L);
        phone.setUpdatedAt(null);
        phone.setCreatedAt(ZonedDateTime.now());
        phone.setAreaCode(phoneRequest.getAreaCode());
        phone.setPhoneNumber(phoneRequest.getPhoneNumber());
        phone.setPhoneType(phoneRequest.getPhoneType());
        return phone;
    }

    public static CustomerProfileEntity buildCustomerProfileEntity() {
        var customerProfileRequest = buildCustomerProfileRequest();
        var customerProfile = new CustomerProfileEntity();
        customerProfile.setUserEntity(buildUserEntity());
        customerProfile.setUpdatedAt(null);
        customerProfile.setCreatedAt(ZonedDateTime.now());
        customerProfile.setFirstName(customerProfileRequest.getFirstName());
        customerProfile.setLastName(customerProfileRequest.getLastName());
        customerProfile.setAddressEntity(buildAddressEntity());
        customerProfile.setPhoneEntity(buildPhoneEntity());
        return customerProfile;
    }

    public static RestaurantProfileEntity buildRestaurantProfileEntity() {
        var restaurantProfileRequest = buildRestaurantProfileRequest();
        var restaurantProfile = new RestaurantProfileEntity();
        restaurantProfile.setUserEntity(buildUserEntity());
        restaurantProfile.setPhoneEntity(buildPhoneEntity());
        restaurantProfile.setAddressEntity(buildAddressEntity());
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