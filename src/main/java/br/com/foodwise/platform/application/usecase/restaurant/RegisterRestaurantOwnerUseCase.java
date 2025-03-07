package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.domain.RestaurantOwner;
import br.com.foodwise.platform.gateway.RestaurantOwnerGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterRestaurantOwnerUseCase {

    private final RestaurantOwnerGateway restaurantOwnerGateway;

    @Transactional
    public void execute(RestaurantOwner restaurantOwner) {
        restaurantOwnerGateway.save(restaurantOwner);
    }
}
