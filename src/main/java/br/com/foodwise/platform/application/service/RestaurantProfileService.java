package br.com.foodwise.platform.application.service;

import br.com.foodwise.platform.application.usecase.restaurant.*;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RegisterRestaurantRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantProfileService {

    private final DeleteRestaurantProfileUseCase deleteRestaurantProfileUseCase;
    private final RegisterRestaurantUseCase registerRestaurantUseCase;
    private final UpdateRestaurantUserEmailUseCase updateRestaurantUserEmailUseCase;
    private final RetrieveRestaurantByBusinessNameUseCase retrieveRestaurantByBusinessNameUseCase;
    private final RetrieveRestaurantByEmailUseCase retrieveRestaurantByEmailUseCase;
    private final UpdateRestaurantProfileUseCase updateRestaurantProfileUseCase;

    public void registerRestaurant(RegisterRestaurantRequest request) {
        registerRestaurantUseCase.execute(request);
    }

    public void updateRestaurantUserEmail(UserRequest userRequest, Long id) {
        updateRestaurantUserEmailUseCase.execute(userRequest, id);
    }

    public RestaurantProfileResponse retrieveRestaurantByBusinessName(String businessName) {
        return retrieveRestaurantByBusinessNameUseCase.execute(businessName);
    }

    public RestaurantProfileResponse retrieveRestaurantByEmail(String email) {
        return retrieveRestaurantByEmailUseCase.execute(email);
    }

    public void updateRestaurantProfile(RestaurantProfileRequest restaurantProfileRequest, Long id) {
        updateRestaurantProfileUseCase.execute(restaurantProfileRequest, id);
    }

    public void delete(long id) {
        deleteRestaurantProfileUseCase.execute(id);
    }

}