package br.com.foodwise.platform.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class Menu {
    private Long id;
    private RestaurantProfile restaurantProfile;
    private String name;
    private String description;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
