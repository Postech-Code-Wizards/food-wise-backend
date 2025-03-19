package br.com.foodwise.platform.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class RestaurantProfile {
    private Long id;
    private String businessName;
    private String description;
    private String businessHours;
    private Short deliveryRadius;
    private String cuisineType;
    private boolean isOpen;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private User user;
    private Address address;
    private Phone phone;

    public RestaurantProfile updateAddress(Address address){
        this.address = address.update(address);
        return this;
    }
}
