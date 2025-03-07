package br.com.foodwise.platform.application.service;

import br.com.foodwise.platform.application.usecase.restaurant.RetrieveRestaurantOwnerUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.DeleteRestaurantProfileUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.RegisterRestaurantOwnerUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.RegisterRestaurantUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.RetrieveRestaurantByBusinessNameUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.RetrieveRestaurantByEmailUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.UpdateRestaurantOwnerUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.UpdateRestaurantProfileUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.UpdateRestaurantUserEmailUseCase;
import br.com.foodwise.platform.domain.RestaurantOwner;
import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantProfileService {

    private final DeleteRestaurantProfileUseCase deleteRestaurantProfileUseCase;
    private final RegisterRestaurantUseCase registerRestaurantUseCase;
    private final RegisterRestaurantOwnerUseCase registerRestaurantOwnerUseCase;
    private final UpdateRestaurantUserEmailUseCase updateRestaurantUserEmailUseCase;
    private final UpdateRestaurantOwnerUseCase updateRestaurantOwnerUseCase;
    private final RetrieveRestaurantByBusinessNameUseCase retrieveRestaurantByBusinessNameUseCase;
    private final RetrieveRestaurantByEmailUseCase retrieveRestaurantByEmailUseCase;
    private final RetrieveRestaurantOwnerUseCase retrieveRestaurantOwnerUseCase;
    private final UpdateRestaurantProfileUseCase updateRestaurantProfileUseCase;

    public void registerRestaurant(RestaurantProfile restaurantProfile) {
        registerRestaurantUseCase.execute(restaurantProfile);
    }

    public void updateRestaurantUserEmail(User user, Long id) {
        updateRestaurantUserEmailUseCase.execute(user, id);
    }

    public RestaurantProfile retrieveRestaurantByBusinessName(String businessName) {
        return null;
    }

    public RestaurantProfile retrieveRestaurantByEmail() {
        return retrieveRestaurantByEmailUseCase.execute();
    }

    public void updateRestaurantProfile(RestaurantProfile restaurantProfile, Long id) {
        updateRestaurantProfileUseCase.execute(restaurantProfile, id);
    }

    public void delete(long id) {
        deleteRestaurantProfileUseCase.execute(id);
    }

    public void registerRestaurantOwner(RestaurantOwner restaurantOwner) {
        registerRestaurantOwnerUseCase.execute(restaurantOwner);
    }

    public void updateRestaurantOwner(RestaurantOwner restaurantOwner, Long userId) {
        updateRestaurantOwnerUseCase.execute(restaurantOwner, userId);
    }

    public RestaurantOwner retrieveRestaurantOwnerById(Long id) {
        return retrieveRestaurantOwnerUseCase.execute(id);

    }
}