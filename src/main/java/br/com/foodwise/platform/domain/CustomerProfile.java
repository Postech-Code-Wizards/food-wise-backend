package br.com.foodwise.platform.domain;

import br.com.foodwise.platform.gateway.entities.PhoneEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class CustomerProfile {
    private Long userId;
    private String firstName;
    private String lastName;
    private Address address;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private User user;
    private PhoneEntity phoneEntity;

}
