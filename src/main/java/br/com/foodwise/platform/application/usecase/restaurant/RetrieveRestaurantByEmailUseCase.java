package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.gateway.RestaurantProfileGateway;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveRestaurantByEmailUseCase {

    private final RestaurantProfileGateway restaurantProfileGateway;

    public RestaurantProfile execute() {

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (UserEntity) authentication.getPrincipal();

        return restaurantProfileGateway
                .findByUserEntityEmail(user.getEmail());
    }
}
