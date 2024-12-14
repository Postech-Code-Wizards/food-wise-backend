package br.com.foodwise.factory;

import br.com.foodwise.model.entities.Address;
import br.com.foodwise.model.entities.Phone;
import br.com.foodwise.model.entities.enums.PhoneType;
import br.com.foodwise.rest.dtos.response.CustomerProfileResponse;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class ResponseFactory {
    public static CustomerProfileResponse buildCustomerProfileResponse() {
        var response = new CustomerProfileResponse();
        response.setFirstName("Test");
        response.setLastName("Value");
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

        response.setAddress(address);
        var phone = new Phone();
        phone.setId(0L);
        phone.setAreaCode("");
        phone.setPhoneNumber("");
        phone.setPhoneType(PhoneType.MOBILE);
        phone.setCreatedAt(ZonedDateTime.now());
        phone.setUpdatedAt(ZonedDateTime.now());
        response.setPhone(phone);
        return response;
    }
}
