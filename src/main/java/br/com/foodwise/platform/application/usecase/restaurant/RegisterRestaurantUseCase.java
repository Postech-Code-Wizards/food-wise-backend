package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.gateway.RestaurantProfileGateway;
import br.com.foodwise.platform.gateway.UserGateway;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterRestaurantUseCase {

    private final RestaurantProfileGateway restaurantProfileGateway;
    private final UserGateway userGateway;

    @Transactional
    public void execute(RestaurantProfile restaurantProfile) {
        if (userGateway.existsByEmail(restaurantProfile.getUser().getEmail())) {
            throw new BusinessException("EMAIL_ALREADY_EXISTS", HttpStatus.CONFLICT, "");
        }

        restaurantProfile.getUser().registerUser(UserType.RESTAURANT_OWNER);
        restaurantProfileGateway.save(restaurantProfile);
    }

}
