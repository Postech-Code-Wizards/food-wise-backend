package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.gateway.entities.RestaurantProfileEntity;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.platform.infrastructure.rest.converter.restaurant.RestaurantProfileRequestToEntityConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class ConvertToRestaurantProfileEntityUseCase {
    private final RestaurantProfileRequestToEntityConverter restaurantProfileRequestToEntityConverter;

    public RestaurantProfileEntity execute(RestaurantProfileRequest restaurantProfileRequest) {
        var restaurant = restaurantProfileRequestToEntityConverter
                .convert(restaurantProfileRequest);
        if (ObjectUtils.isEmpty(restaurant)) {
            throw new ResourceNotFoundException("RESTAURANT_PROFILE_EXCEPTION");
        }
        return restaurant;
    }

}
