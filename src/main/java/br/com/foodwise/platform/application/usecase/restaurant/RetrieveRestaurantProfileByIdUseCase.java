package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.gateway.RestaurantProfileGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveRestaurantProfileByIdUseCase {
    private final RestaurantProfileGateway restaurantProfileGateway;

    public RestaurantProfile retrieveById(Long id) {
        return restaurantProfileGateway.findById(id);
    }
}
