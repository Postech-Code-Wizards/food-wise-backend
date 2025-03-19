package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.gateway.RestaurantProfileGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class UpdateRestaurantProfileUseCase {

    private final RestaurantProfileGateway restaurantProfileGateway;

    private static RestaurantProfile populate(RestaurantProfile restaurantProfile, RestaurantProfile existingRestaurantProfile) {
        return new RestaurantProfile(
                existingRestaurantProfile.getId(),
                restaurantProfile.getBusinessName(),
                restaurantProfile.getDescription(),
                restaurantProfile.getBusinessHours(),
                restaurantProfile.getDeliveryRadius(),
                restaurantProfile.getCuisineType(),
                existingRestaurantProfile.isOpen(),
                restaurantProfile.getIsDeliveryOrder(),
                existingRestaurantProfile.getCreatedAt(),
                ZonedDateTime.now(),
                existingRestaurantProfile.getUser(),
                restaurantProfile.getAddress(),
                restaurantProfile.getPhone()
        );
    }

    @Transactional
    public void execute(RestaurantProfile restaurantProfile, Long id) {
        var existingRestaurantProfile = restaurantProfileGateway.findById(id);

        var restaurantProfileUpdate = populate(restaurantProfile, existingRestaurantProfile);
        restaurantProfileGateway.save(restaurantProfileUpdate);
    }
}
