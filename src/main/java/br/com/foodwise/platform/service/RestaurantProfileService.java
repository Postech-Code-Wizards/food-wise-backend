package br.com.foodwise.platform.service;

import br.com.foodwise.platform.model.entities.RestaurantProfile;
import br.com.foodwise.platform.model.entities.enums.UserType;
import br.com.foodwise.platform.model.repositories.RestaurantProfileRepository;
import br.com.foodwise.platform.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.platform.rest.converter.restaurant.RestaurantProfileEntityToResponseConverter;
import br.com.foodwise.platform.rest.converter.restaurant.RestaurantProfileRequestToEntityConverter;
import br.com.foodwise.platform.rest.dtos.request.register.restaurant.RegisterRestaurantRequest;
import br.com.foodwise.platform.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import br.com.foodwise.platform.rest.dtos.response.RestaurantProfileResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class RestaurantProfileService {

    private final RestaurantProfileRepository restaurantProfileRepository;

    private final RestaurantProfileRequestToEntityConverter restaurantProfileRequestToEntityConverter;
    private final RestaurantProfileEntityToResponseConverter restaurantProfileEntityToResponseConverter;
    private final UserService userService;

    @Transactional
    public void registerRestaurant(RegisterRestaurantRequest request) {
        var userRequest = request.getUser();
        var user = userService.createUser(userRequest.getEmail(), userRequest.getPassword(), UserType.RESTAURANT_OWNER);

        var restaurantRequest = request.getRestaurant();
        var newRestaurant = this.convertToRestaurantProfileEntity(restaurantRequest);
        newRestaurant.setUser(user);
        restaurantProfileRepository.save(newRestaurant);
    }

    public RestaurantProfileResponse retrieveRestaurantByBusinessName(String businessName) {
        var restaurantProfile = restaurantProfileRepository
                .findByBusinessName(businessName).orElseThrow(() -> new ResourceNotFoundException("Restaurante " + businessName));
        return convertToRestaurantProfileResponse(restaurantProfile);
    }

    public RestaurantProfileResponse retrieveRestaurantByEmail(String email) {
        var restaurantProfile = restaurantProfileRepository
                .findByUserEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email " + email));
        return convertToRestaurantProfileResponse(restaurantProfile);
    }


    public RestaurantProfile convertToRestaurantProfileEntity(RestaurantProfileRequest restaurantProfileRequest) {
        var restaurant = restaurantProfileRequestToEntityConverter
                .convert(restaurantProfileRequest);
        if (ObjectUtils.isEmpty(restaurant)) {
            throw new IllegalArgumentException("Restaurant profile conversion failed.");
        }
        return restaurant;
    }

    private RestaurantProfileResponse convertToRestaurantProfileResponse(RestaurantProfile restaurantProfile) {
        return restaurantProfileEntityToResponseConverter
                .convert(restaurantProfile);
    }

    @Transactional
    public void delete(long id) {
        userService.delete(id, UserType.RESTAURANT_OWNER);
    }

}
