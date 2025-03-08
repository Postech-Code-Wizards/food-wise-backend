package br.com.foodwise.platform.infrastructure.rest.converter.restaurant;

import br.com.foodwise.platform.domain.RestaurantOwner;
import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RegisterRestaurantOwnerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class RestaurantOwnerRequestToDomainConverter {

    public RestaurantOwner convert(RegisterRestaurantOwnerRequest source, RestaurantProfile restaurantProfile) {
        return new RestaurantOwner(
                null,
                source.getFirstName(),
                source.getLastName(),
                source.getBusinessRegistrationNumber(),
                source.getBusinessEmail(),
                ZonedDateTime.now(),
                ZonedDateTime.now(),
                restaurantProfile.getUser()
        );
    }

    public RestaurantOwner convert(RegisterRestaurantOwnerRequest source) {
        return new RestaurantOwner(
                null,
                source.getFirstName(),
                source.getLastName(),
                source.getBusinessRegistrationNumber(),
                source.getBusinessEmail(),
                null,
                null,
                null
        );
    }
}
