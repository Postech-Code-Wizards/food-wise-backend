package br.com.foodWise.foodWise.service;

import br.com.foodWise.foodWise.model.entities.RestaurantProfile;
import br.com.foodWise.foodWise.model.entities.User;
import br.com.foodWise.foodWise.model.repositories.RestaurantProfileRepository;
import br.com.foodWise.foodWise.rest.dtos.request.register.RegisterRestaurantRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantProfileService {

    private final RestaurantProfileRepository restaurantProfileRepository;

    public void createRestaurantProfile(User user) {
        var restaurantProfile = new RestaurantProfile();
        restaurantProfile.setUser(user);
    }

    public void registerRestaurant(RegisterRestaurantRequest request) {

    }
}
