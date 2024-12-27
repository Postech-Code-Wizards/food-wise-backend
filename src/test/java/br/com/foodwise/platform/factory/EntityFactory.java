package br.com.foodwise.platform.factory;

import br.com.foodwise.platform.model.entities.Address;
import br.com.foodwise.platform.model.entities.CustomerProfile;
import br.com.foodwise.platform.model.entities.Phone;
import br.com.foodwise.platform.model.entities.RestaurantProfile;
import br.com.foodwise.platform.model.entities.enums.PhoneType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static br.com.foodwise.platform.factory.SecurityHelperFactory.buildMockUser;

public class EntityFactory {
    public static CustomerProfile buildCustomerProfile() {
        var customerProfile = new CustomerProfile();
        customerProfile.setFirstName("");
        customerProfile.setLastName("");
        customerProfile.setAddress(buildAddress());
        customerProfile.setCreatedAt(ZonedDateTime.now());
        customerProfile.setUpdatedAt(ZonedDateTime.now());
        customerProfile.setUser(SecurityHelperFactory.buildMockUser());
        customerProfile.setPhone(buildPhone());
        return customerProfile;
    }

    public static RestaurantProfile buildRestaurantProfile() {
        var restaurantProfile = new RestaurantProfile();
        restaurantProfile.setBusinessName("");
        restaurantProfile.setDescription("");
        restaurantProfile.setBusinessHours("");
        restaurantProfile.setDeliveryRadius((short) 0);
        restaurantProfile.setCuisineType("");
        restaurantProfile.setOpen(false);
        restaurantProfile.setCreatedAt(ZonedDateTime.now());
        restaurantProfile.setUpdatedAt(ZonedDateTime.now());
        restaurantProfile.setUser(SecurityHelperFactory.buildMockUser());
        restaurantProfile.setAddress(buildAddress());
        restaurantProfile.setPhone(buildPhone());
        return restaurantProfile;
    }

    public static Address buildAddress() {
        var address = new Address();
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

    public static Phone buildPhone() {
        var phone = new Phone();
        phone.setId(0L);
        phone.setAreaCode("");
        phone.setPhoneNumber("");
        phone.setPhoneType(PhoneType.MOBILE);
        phone.setCreatedAt(ZonedDateTime.now());
        phone.setUpdatedAt(ZonedDateTime.now());
        return phone;
    }

}
