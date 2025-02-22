package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.domain.entities.RestaurantProfile;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.platform.infrastructure.rest.converter.restaurant.RestaurantProfileEntityToResponseConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.restaurant.RestaurantProfileRequestToEntityConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class ConvertToRestaurantProfileEntityUseCase {

    private final RestaurantProfileEntityToResponseConverter restaurantProfileEntityToResponseConverter;
    private final RestaurantProfileRequestToEntityConverter restaurantProfileRequestToEntityConverter;

    public RestaurantProfileResponse execute(RestaurantProfile restaurantProfile) {
        return restaurantProfileEntityToResponseConverter
                .convert(restaurantProfile);
    }

    public RestaurantProfile execute(RestaurantProfileRequest restaurantProfileRequest) {
        var restaurant = restaurantProfileRequestToEntityConverter
                .convert(restaurantProfileRequest);
        if (ObjectUtils.isEmpty(restaurant)) {
            throw new ResourceNotFoundException("RESTAURANT_PROFILE_EXCEPTION");
        }
        return restaurant;
    }

}
