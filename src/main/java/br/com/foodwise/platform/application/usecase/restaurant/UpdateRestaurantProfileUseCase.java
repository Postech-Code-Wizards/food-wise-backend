package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.gateway.repository.RestaurantProfileRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class UpdateRestaurantProfileUseCase {

    private final RestaurantProfileRepository restaurantProfileRepository;
    private final ConvertToRestaurantProfileEntityUseCase convertToRestaurantProfileEntityUseCase;

    @Transactional
    public void execute(RestaurantProfileRequest restaurantProfileRequest, Long id) {
        var existingRestaurantProfile = restaurantProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RESTAURANT_DOES_NOT_EXIST", ""));

        var restaurantProfile = convertToRestaurantProfileEntityUseCase.execute(restaurantProfileRequest);
        existingRestaurantProfile.setBusinessName(restaurantProfile.getBusinessName());
        existingRestaurantProfile.setDescription(restaurantProfile.getDescription());
        existingRestaurantProfile.setBusinessHours(restaurantProfile.getBusinessHours());
        existingRestaurantProfile.setDeliveryRadius(restaurantProfile.getDeliveryRadius());
        existingRestaurantProfile.setCuisineType(restaurantProfile.getCuisineType());
        existingRestaurantProfile.setUpdatedAt(ZonedDateTime.now());
        existingRestaurantProfile.setAddressEntity(restaurantProfile.getAddressEntity());
        existingRestaurantProfile.setPhoneEntity(restaurantProfile.getPhoneEntity());

        restaurantProfileRepository.save(existingRestaurantProfile);
    }
}
