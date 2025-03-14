package br.com.foodwise.platform.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class RestaurantOwner {
    private Long id;
    private String firstName;
    private String lastName;
    private String businessRegistrationNumber;
    private String businessEmail;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    @JsonIgnore
    private User user;

}
