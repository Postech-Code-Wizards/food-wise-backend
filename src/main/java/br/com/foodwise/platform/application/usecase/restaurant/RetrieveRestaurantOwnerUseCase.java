package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.domain.RestaurantOwner;
import br.com.foodwise.platform.gateway.RestaurantOwnerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveRestaurantOwnerUseCase {

    private final RestaurantOwnerGateway restaurantOwnerGateway;

    public RestaurantOwner execute(Long id) {
        return restaurantOwnerGateway.findById(id);
    }
}
