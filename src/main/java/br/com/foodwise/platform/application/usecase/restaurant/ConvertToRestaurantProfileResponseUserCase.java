package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.domain.entities.RestaurantProfile;
import br.com.foodwise.platform.infrastructure.rest.converter.restaurant.RestaurantProfileEntityToResponseConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConvertToRestaurantProfileResponseUserCase {

    private final RestaurantProfileEntityToResponseConverter restaurantProfileEntityToResponseConverter;

    public RestaurantProfileResponse execute(RestaurantProfile restaurantProfile) {
        return restaurantProfileEntityToResponseConverter
                .convert(restaurantProfile);
    }

}
