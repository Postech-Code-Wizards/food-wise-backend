package br.com.foodwise.platform.gateway;

import br.com.foodwise.platform.domain.RestaurantOwner;

public interface RestaurantOwnerGateway {

    RestaurantOwner findById(Long id);

    void save(RestaurantOwner restaurantOwner);
}
