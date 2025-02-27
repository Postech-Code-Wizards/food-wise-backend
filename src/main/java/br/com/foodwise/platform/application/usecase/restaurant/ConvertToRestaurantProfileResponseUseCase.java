package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.gateway.entities.RestaurantProfileEntity;
import br.com.foodwise.platform.infrastructure.rest.converter.restaurant.RestaurantProfileEntityToResponseConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConvertToRestaurantProfileResponseUseCase {

    private final RestaurantProfileEntityToResponseConverter restaurantProfileEntityToResponseConverter;

    public RestaurantProfileResponse execute(RestaurantProfileEntity restaurantProfileEntity) {
        return restaurantProfileEntityToResponseConverter
                .convert(restaurantProfileEntity);
    }

}
