package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.domain.repository.RestaurantProfileRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveRestaurantByBusinessNameUseCase {

    private final RestaurantProfileRepository restaurantProfileRepository;
    private final ConvertToRestaurantProfileEntityUseCase convertToRestaurantProfileEntityUseCase;

    public RestaurantProfileResponse execute(String businessName) {
        var restaurantProfile = restaurantProfileRepository
                .findByBusinessName(businessName).orElseThrow(() -> new ResourceNotFoundException("Restaurante " + businessName));
        return convertToRestaurantProfileEntityUseCase.execute(restaurantProfile);
    }
}
