package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.domain.RestaurantOwner;
import br.com.foodwise.platform.gateway.RestaurantOwnerGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class UpdateRestaurantOwnerUseCase {

    private final RestaurantOwnerGateway restaurantOwnerGateway;

    private static RestaurantOwner populate(RestaurantOwner existingOwner, RestaurantOwner restaurantOwner) {
        return new RestaurantOwner(
                existingOwner.getId(),
                restaurantOwner.getFirstName(),
                restaurantOwner.getLastName(),
                restaurantOwner.getBusinessRegistrationNumber(),
                restaurantOwner.getBusinessEmail(),
                existingOwner.getCreatedAt(),
                ZonedDateTime.now(),
                existingOwner.getUser()
        );
    }

    @Transactional
    public void execute(RestaurantOwner restaurantOwner, Long userId) {
        var existingOwner = restaurantOwnerGateway.findById(userId);

        RestaurantOwner restaurantOwnerSave = populate(existingOwner, restaurantOwner);
        restaurantOwnerGateway.save(restaurantOwnerSave);

    }
}
