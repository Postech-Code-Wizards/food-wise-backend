package br.com.foodwise.platform.application.facade;

import br.com.foodwise.platform.application.usecase.restaurant.*;
import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.application.facade.converter.common.UserRequestToDomainConverter;
import br.com.foodwise.platform.application.facade.converter.restaurant.RestaurantProfileDomainToResponseConverter;
import br.com.foodwise.platform.application.facade.converter.restaurant.RestaurantProfileRequestToDomainConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RegisterRestaurantRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantProfileFacade {

    private final DeleteRestaurantProfileUseCase deleteRestaurantProfileUseCase;
    private final RegisterRestaurantUseCase registerRestaurantUseCase;
    private final UpdateRestaurantUserEmailUseCase updateRestaurantUserEmailUseCase;
    private final RetrieveRestaurantByBusinessNameUseCase retrieveRestaurantByBusinessNameUseCase;
    private final RetrieveRestaurantByEmailUseCase retrieveRestaurantByEmailUseCase;
    private final UpdateRestaurantProfileUseCase updateRestaurantProfileUseCase;
    private final RestaurantProfileRequestToDomainConverter restaurantProfileRequestToDomainConverter;
    private final RestaurantProfileDomainToResponseConverter restaurantProfileDomainToResponseConverter;
    private final UserRequestToDomainConverter userRequestToDomainConverter;

    public void registerRestaurant(RegisterRestaurantRequest restaurantProfile) {
        User user = userRequestToDomainConverter.convert(restaurantProfile.getUser());
        RestaurantProfile restaurantProfileConverted = restaurantProfileRequestToDomainConverter.convert(restaurantProfile.getRestaurant(), user);
        registerRestaurantUseCase.execute(restaurantProfileConverted);
    }

    public void updateRestaurantUserEmail(UserRequest userRequest, Long id) {
        var user = userRequestToDomainConverter.convert(userRequest);
        updateRestaurantUserEmailUseCase.execute(user, id);
    }

    public RestaurantProfileResponse retrieveRestaurantByBusinessName(String businessName) {
        RestaurantProfile restaurantProfile = retrieveRestaurantByBusinessNameUseCase.execute(businessName);
        return restaurantProfileDomainToResponseConverter.convert(restaurantProfile);
    }

    public RestaurantProfileResponse retrieveRestaurantByEmail() {
        var restaurantProfile = retrieveRestaurantByEmailUseCase.execute();
        return restaurantProfileDomainToResponseConverter.convert(restaurantProfile);
    }

    public void updateRestaurantProfile(RestaurantProfileRequest restaurantProfileRequest, Long id) {
        RestaurantProfile restaurantProfile = restaurantProfileRequestToDomainConverter.convert(restaurantProfileRequest);
        updateRestaurantProfileUseCase.execute(restaurantProfile, id);
    }

    public void delete(long id) {
        deleteRestaurantProfileUseCase.execute(id);
    }

}