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

import java.time.ZonedDateTime;

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

    @Transactional
    public void updateRestaurantProfile(RestaurantProfileRequest restaurantProfileRequest, Long id) {
        var existingRestaurantProfile = restaurantProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RESTAURANT_DOES_NOT_EXIST", ""));

        var restaurantProfile = convertToRestaurantProfileEntity(restaurantProfileRequest);

        existingRestaurantProfile.setBusinessName(restaurantProfile.getBusinessName());
        existingRestaurantProfile.setDescription(restaurantProfile.getDescription());
        existingRestaurantProfile.setBusinessHours(restaurantProfile.getBusinessHours());
        existingRestaurantProfile.setDeliveryRadius(restaurantProfile.getDeliveryRadius());
        existingRestaurantProfile.setCuisineType(restaurantProfile.getCuisineType());
        existingRestaurantProfile.setUpdatedAt(ZonedDateTime.now());
        existingRestaurantProfile.setAddress(restaurantProfile.getAddress());
        existingRestaurantProfile.setPhone(restaurantProfile.getPhone());

        restaurantProfileRepository.save(existingRestaurantProfile);
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
            throw new ResourceNotFoundException("RESTAURANT_PROFILE_EXCEPTION");
        }
        return restaurant;
    }

    private RestaurantProfileResponse convertToRestaurantProfileResponse(RestaurantProfile restaurantProfile) {
        return restaurantProfileEntityToResponseConverter
                .convert(restaurantProfile);
    }
}
