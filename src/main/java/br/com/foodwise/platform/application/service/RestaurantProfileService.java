package br.com.foodwise.platform.application.service;

import br.com.foodwise.platform.application.usecase.restaurant.*;
import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.infrastructure.rest.converter.restaurant.RestaurantProfileDomainToIsDeliveryRestaurantResponse;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.IsDeliveryRestaurantResponse;
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
    private final RetrieveRestaurantByIdUseCase retrieveRestaurantByIdUseCase;
    private final UpdateRestaurantProfileUseCase updateRestaurantProfileUseCase;
    private final RestaurantProfileDomainToIsDeliveryRestaurantResponse restaurantProfileDomainToIsDeliveryRestaurantResponse;



    public void registerRestaurant(RestaurantProfile restaurantProfile) {
        registerRestaurantUseCase.execute(restaurantProfile);
    }

    public void updateRestaurantUserEmail(User user, Long id) {
        updateRestaurantUserEmailUseCase.execute(user, id);
    }

    public RestaurantProfile retrieveRestaurantByBusinessName(String businessName) {
        return retrieveRestaurantByBusinessNameUseCase.execute(businessName);
    }

    public RestaurantProfile retrieveRestaurantByEmail() {
        return retrieveRestaurantByEmailUseCase.execute();
    }

    public IsDeliveryRestaurantResponse retrieveRestaurantById(Long id){

        var restaurantProfile = retrieveRestaurantByIdUseCase.execute(id);
        var response = restaurantProfileDomainToIsDeliveryRestaurantResponse.convert(restaurantProfile);



        return response;}

    public void updateRestaurantProfile(RestaurantProfile restaurantProfile, Long id) {
        updateRestaurantProfileUseCase.execute(restaurantProfile, id);
    }

    public void delete(long id) {
        deleteRestaurantProfileUseCase.execute(id);
    }

}