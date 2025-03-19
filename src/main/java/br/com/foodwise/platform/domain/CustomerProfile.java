package br.com.foodwise.platform.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class CustomerProfile {
    private Long id;
    private String firstName;
    private String lastName;
    private Address address;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private User user;
    private Phone phone;

    public CustomerProfile updateAddress(Address address) {
        this.address = address.update(address);
        return this;
    }
}
