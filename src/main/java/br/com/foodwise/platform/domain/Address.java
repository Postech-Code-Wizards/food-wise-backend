package br.com.foodwise.platform.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class Address {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String neighborhood;
    private String postalCode;
    private String country;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public Address update(Address address) {
        this.street = address.getStreet();
        this.city = address.getCity();
        this.state = address.getState();
        this.neighborhood = address.getNeighborhood();
        this.postalCode = address.getPostalCode();
        this.country = address.getCountry();
        this.latitude = address.getLatitude();
        this.longitude = address.getLongitude();

        return this;
    }

}
