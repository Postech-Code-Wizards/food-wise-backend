package br.com.foodwise.platform.service;

import br.com.foodwise.platform.domain.entities.RestaurantOwner;
import br.com.foodwise.platform.domain.entities.RestaurantProfile;
import br.com.foodwise.platform.domain.entities.enums.UserType;
import br.com.foodwise.platform.domain.repository.RestaurantOwnerRepository;
import br.com.foodwise.platform.domain.repository.RestaurantProfileRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.platform.infrastructure.rest.converter.restaurant.RegisterRestaurantOwnerRequestToEntityConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.restaurant.RestaurantProfileEntityToResponseConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.restaurant.RestaurantProfileRequestToEntityConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RegisterRestaurantOwnerRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RegisterRestaurantRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class RestaurantProfileService {

    private final RestaurantProfileRepository restaurantProfileRepository;
    private final RestaurantOwnerRepository restaurantOwnerRepository;

    private final RestaurantProfileRequestToEntityConverter restaurantProfileRequestToEntityConverter;
    private final RestaurantProfileEntityToResponseConverter restaurantProfileEntityToResponseConverter;
    private final RegisterRestaurantOwnerRequestToEntityConverter registerRestaurantOwnerRequestToEntityConverter;
    private final UserService userService;

    @Transactional
    public void registerRestaurant(RegisterRestaurantRequest request) {
        var userRequest = request.getUser();
        var user = userService.createUser(userRequest.getEmail(), userRequest.getPassword(), UserType.RESTAURANT_OWNER);

        var restaurantRequest = request.getRestaurant();
        var newRestaurant = this.convertToRestaurantProfileEntity(restaurantRequest);
        newRestaurant.setUser(user);
        restaurantProfileRepository.save(newRestaurant);

        var restaurantOwnerRequest = request.getOwner();
        var newRestaurantOwner = this.convertToRestaurantOwnerEntity(restaurantOwnerRequest);
        newRestaurantOwner.setUser(user);
        restaurantOwnerRepository.save(newRestaurantOwner);
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

    @Transactional
    public void updateRestaurantUserEmail(UserRequest userRequest, Long id) {
        userService.updateUserEmail(userRequest, id, UserType.RESTAURANT_OWNER);
    }

    @Transactional
    public void updateRestaurantOwner(RegisterRestaurantOwnerRequest registerRestaurantOwnerRequest, Long userId) {
        var existingRestaurantOwner = restaurantOwnerRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador"));

        var restaurantOwner = convertToRestaurantOwnerEntity(registerRestaurantOwnerRequest);

        existingRestaurantOwner.setFirstName(restaurantOwner.getFirstName());
        existingRestaurantOwner.setLastName(restaurantOwner.getLastName());
        existingRestaurantOwner.setBusinessRegistrationNumber(restaurantOwner.getBusinessRegistrationNumber());
        existingRestaurantOwner.setBusinessEmail(restaurantOwner.getBusinessEmail());

        restaurantOwnerRepository.save(existingRestaurantOwner);
    }

    public RestaurantProfileResponse retrieveRestaurantByBusinessName(String businessName) {
        var restaurantProfile = restaurantProfileRepository
                .findByBusinessName(businessName).orElseThrow(() -> new ResourceNotFoundException("Restaurante " + businessName));
        return convertToRestaurantProfileResponse(restaurantProfile);
    }

    public RestaurantProfileResponse retrieveRestaurantByEmail(String email) {
        var restaurantProfile = restaurantProfileRepository
                .findByUserEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email " + email));
        var restaurantOwner = restaurantOwnerRepository.findById(restaurantProfile.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant Owner " + restaurantProfile.getUser().getId()));
        var restaurantProfileResponse = convertToRestaurantProfileResponse(restaurantProfile);
        restaurantProfileResponse.setRestaurantOwner(restaurantOwner);
        return restaurantProfileResponse;
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

    private RestaurantOwner convertToRestaurantOwnerEntity(RegisterRestaurantOwnerRequest restaurantOwnerRequest) {
        var restaurantOwner = registerRestaurantOwnerRequestToEntityConverter
                .convert(restaurantOwnerRequest);
        if (ObjectUtils.isEmpty(restaurantOwner)) {
            throw new ResourceNotFoundException("Owner");
        }
        return restaurantOwner;
    }

    @Transactional
    public void delete(long id) {
        userService.delete(id, UserType.RESTAURANT_OWNER);
    }

}
