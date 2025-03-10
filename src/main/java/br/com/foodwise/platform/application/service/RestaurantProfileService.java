package br.com.foodwise.platform.application.service;

import br.com.foodwise.platform.application.usecase.restaurant.DeleteRestaurantProfileUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.RegisterRestaurantOwnerUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.RegisterRestaurantUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.RetrieveRestaurantByBusinessNameUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.RetrieveRestaurantByEmailUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.RetrieveRestaurantOwnerUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.UpdateRestaurantOwnerUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.UpdateRestaurantProfileUseCase;
import br.com.foodwise.platform.application.usecase.restaurant.UpdateRestaurantUserEmailUseCase;
import br.com.foodwise.platform.domain.RestaurantOwner;
import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.infrastructure.rest.converter.common.UserRequestToDomainConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.restaurant.RestaurantOwnerRequestToDomainConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.restaurant.RestaurantProfileRequestToDomainConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RegisterRestaurantRequest;
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

    private final RestaurantProfileRequestToDomainConverter restaurantProfileRequestToDomainConverter;
    private final RestaurantOwnerRequestToDomainConverter restaurantOwnerRequestToDomainConverter;
    private final UserRequestToDomainConverter userRequestToDomainConverter;


    public void registerRestaurant(RegisterRestaurantRequest restaurantRequest) {
        User user = userRequestToDomainConverter.convert(restaurantRequest.getUser());
        RestaurantProfile restaurantProfile = restaurantProfileRequestToDomainConverter.convert(restaurantRequest.getRestaurant(), user);

        var restaurant = registerRestaurantUseCase.execute(restaurantProfile);

        /*var restaurantUser = retrieveRestaurantByEmail(restaurant.getUser().getEmail());*/
        /*user = findUserByEmailUseCase.findUserByEmail(restaurant.getUser().getEmail());*/
        var restaurantOwner = restaurantOwnerRequestToDomainConverter.convert(restaurantRequest.getOwner(), restaurant.getUser());
        registerRestaurantOwnerUseCase.execute(restaurantOwner);
    }

    public void updateRestaurantUserEmail(User user, Long id) {
        updateRestaurantUserEmailUseCase.execute(user, id);
    }

    public RestaurantProfile retrieveRestaurantByBusinessName(String businessName) {
        return null;
    }

    public RestaurantProfile retrieveRestaurantByEmail(String email) {
        return retrieveRestaurantByEmailUseCase.execute(email);
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