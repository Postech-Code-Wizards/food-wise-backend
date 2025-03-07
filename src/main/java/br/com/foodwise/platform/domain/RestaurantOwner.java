package br.com.foodwise.platform.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class RestaurantOwner {
    private Long userId;
    private String firstName;
    private String lastName;
    private String businessRegistrationNumber;
    private String businessEmail;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private User user;

}
