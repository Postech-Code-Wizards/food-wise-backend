package br.com.foodwise.platform.factory;

import br.com.foodwise.platform.domain.*;
import br.com.foodwise.platform.domain.enums.PhoneType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class DomainFactory {
    public static CustomerProfile buildCustomerProfile() {
        return new CustomerProfile(
                0L,
                "",
                "",
                buildAddress(),
                ZonedDateTime.now(),
                ZonedDateTime.now(),
                SecurityHelperFactory.buildMockUser(),
                buildPhone()

        );
    }

    public static RestaurantProfile buildRestaurantProfile() {
        return new RestaurantProfile(
                0L,
                "",
                "",
                "",
                (short) 0,
                "",
                false,
                ZonedDateTime.now(),
                ZonedDateTime.now(),
                SecurityHelperFactory.buildMockUser(),
                buildAddress(),
                buildPhone()
        );
    }

    public static Address buildAddress() {
        return new Address(
                0L,
                "",
                "",
                "",
                "",
                "",
                "",
                new BigDecimal("0"),
                new BigDecimal("0"),
                ZonedDateTime.now(),
                ZonedDateTime.now()
        );
    }

    public static Phone buildPhone() {
        return new Phone(
                0L,
                "",
                "",
                PhoneType.MOBILE,
                ZonedDateTime.now(),
                ZonedDateTime.now()
        );
    }

    public static Menu buildMenu() {
        return new Menu(
                0L,
                buildRestaurantProfile(),
                "",
                "",
                ZonedDateTime.now(),
                ZonedDateTime.now()
        );
    }
}
