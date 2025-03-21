package br.com.foodwise.platform.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class RestaurantReview {
    private Long id;
    private CustomerProfile customerProfile;
    private RestaurantProfile restaurantProfile;
    private Short rating;
    private String reviewText;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

}
