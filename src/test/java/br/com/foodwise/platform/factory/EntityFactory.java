package br.com.foodwise.platform.factory;

import br.com.foodwise.platform.gateway.database.jpa.entities.*;
import br.com.foodwise.platform.domain.enums.PhoneType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static br.com.foodwise.platform.factory.SecurityHelperFactory.buildMockUser;

public class EntityFactory {
    public static CustomerProfileEntity buildCustomerProfile() {
        var customerProfile = new CustomerProfileEntity();
        customerProfile.setFirstName("");
        customerProfile.setLastName("");
        customerProfile.setAddressEntity(buildAddress());
        customerProfile.setCreatedAt(ZonedDateTime.now());
        customerProfile.setUpdatedAt(ZonedDateTime.now());
        customerProfile.setUserEntity(SecurityHelperFactory.buildMockUser());
        customerProfile.setPhoneEntity(buildPhone());
        return customerProfile;
    }

    public static RestaurantProfileEntity buildRestaurantProfile() {
        var restaurantProfile = new RestaurantProfileEntity();
        restaurantProfile.setBusinessName("");
        restaurantProfile.setDescription("");
        restaurantProfile.setBusinessHours("");
        restaurantProfile.setDeliveryRadius((short) 0);
        restaurantProfile.setCuisineType("");
        restaurantProfile.setOpen(false);
        restaurantProfile.setCreatedAt(ZonedDateTime.now());
        restaurantProfile.setUpdatedAt(ZonedDateTime.now());
        restaurantProfile.setUserEntity(SecurityHelperFactory.buildMockUser());
        restaurantProfile.setAddressEntity(buildAddress());
        restaurantProfile.setPhoneEntity(buildPhone());
        return restaurantProfile;
    }

    public static AddressEntity buildAddress() {
        var address = new AddressEntity();
        address.setId(0L);
        address.setStreet("");
        address.setCity("");
        address.setState("");
        address.setNeighborhood("");
        address.setPostalCode("");
        address.setCountry("");
        address.setLatitude(new BigDecimal("0"));
        address.setLongitude(new BigDecimal("0"));
        address.setCreatedAt(ZonedDateTime.now());
        address.setUpdatedAt(ZonedDateTime.now());
        return address;
    }

    public static PhoneEntity buildPhone() {
        var phone = new PhoneEntity();
        phone.setId(0L);
        phone.setAreaCode("");
        phone.setPhoneNumber("");
        phone.setPhoneType(PhoneType.MOBILE);
        phone.setCreatedAt(ZonedDateTime.now());
        phone.setUpdatedAt(ZonedDateTime.now());
        return phone;
    }

    public static MenuEntity buildMenu() {
        var menu = new MenuEntity();
        menu.setId(0L);
        RestaurantProfileEntity restaurantProfileEntity = new RestaurantProfileEntity();
        restaurantProfileEntity.setUserId(0L);
        restaurantProfileEntity.setBusinessName("");
        restaurantProfileEntity.setDescription("");
        restaurantProfileEntity.setBusinessHours("");
        restaurantProfileEntity.setDeliveryRadius((short) 0);
        restaurantProfileEntity.setCuisineType("");
        restaurantProfileEntity.setOpen(false);
        restaurantProfileEntity.setCreatedAt(ZonedDateTime.now());
        restaurantProfileEntity.setUpdatedAt(ZonedDateTime.now());
        restaurantProfileEntity.setUserEntity(buildMockUser());
        restaurantProfileEntity.setAddressEntity(buildAddress());
        restaurantProfileEntity.setPhoneEntity(buildPhone());
        return menu;
    }
}
