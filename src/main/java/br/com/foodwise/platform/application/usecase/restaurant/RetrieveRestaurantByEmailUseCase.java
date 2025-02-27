package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.gateway.repository.RestaurantProfileRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveRestaurantByEmailUseCase {

    private final RestaurantProfileRepository restaurantProfileRepository;
    private final ConvertToRestaurantProfileResponseUseCase convertToRestaurantProfileResponse;

    public RestaurantProfileResponse execute(String email) {
        var restaurantProfile = restaurantProfileRepository
                .findByUserEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email " + email));
        return convertToRestaurantProfileResponse.execute(restaurantProfile);
    }

}
