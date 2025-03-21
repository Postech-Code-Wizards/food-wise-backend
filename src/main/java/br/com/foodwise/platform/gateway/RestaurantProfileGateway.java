package br.com.foodwise.platform.gateway;

import br.com.foodwise.platform.domain.RestaurantProfile;

public interface RestaurantProfileGateway {

    RestaurantProfile findById(long id);

    RestaurantProfile findByUserEntityEmail(String email);

    RestaurantProfile findByBusinessName(String businessName);

    RestaurantProfile save(RestaurantProfile restaurantProfile);

}
