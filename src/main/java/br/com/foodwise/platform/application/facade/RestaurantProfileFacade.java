package br.com.foodwise.platform.application.facade;

import br.com.foodwise.platform.application.facade.converter.common.UserRequestToDomainConverter;
import br.com.foodwise.platform.application.facade.converter.restaurant.RestaurantOwnerRequestToDomainConverter;
import br.com.foodwise.platform.application.facade.converter.restaurant.RestaurantProfileDomainToResponseConverter;
import br.com.foodwise.platform.application.facade.converter.restaurant.RestaurantProfileRequestToDomainConverter;
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
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RegisterRestaurantOwnerRequest;
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
    private final RegisterRestaurantOwnerUseCase registerRestaurantOwnerUseCase;
    private final RetrieveRestaurantOwnerUseCase retrieveRestaurantOwnerUseCase;
    private final UpdateRestaurantOwnerUseCase updateRestaurantOwnerUseCase;

    private final RestaurantProfileRequestToDomainConverter restaurantProfileRequestToDomainConverter;
    private final RestaurantProfileDomainToResponseConverter restaurantProfileDomainToResponseConverter;
    private final UserRequestToDomainConverter userRequestToDomainConverter;
    private final RestaurantOwnerRequestToDomainConverter restaurantOwnerRequestToDomainConverter;


    public void registerRestaurant(RegisterRestaurantRequest restaurantProfile) {
        User user = userRequestToDomainConverter.convert(restaurantProfile.getUser());
        RestaurantProfile restaurantProfileConverted = restaurantProfileRequestToDomainConverter.convert(restaurantProfile.getRestaurant(), user);

        RestaurantProfile restaurant = registerRestaurantUseCase.execute(restaurantProfileConverted);

        RestaurantOwner restaurantOwner = restaurantOwnerRequestToDomainConverter.convert(restaurantProfile.getOwner(), restaurant.getUser());
        registerRestaurantOwnerUseCase.execute(restaurantOwner);
    }

    public void updateRestaurantUserEmail(UserRequest userRequest, Long id) {
        var user = userRequestToDomainConverter.convert(userRequest);
        updateRestaurantUserEmailUseCase.execute(user, id);
    }

    public RestaurantProfileResponse retrieveRestaurantByBusinessName(String businessName) {
        RestaurantProfile restaurantProfile = retrieveRestaurantByBusinessNameUseCase.execute(businessName);
        return restaurantProfileDomainToResponseConverter.convert(restaurantProfile);
    }

    public RestaurantProfileResponse retrieveRestaurantByEmail(String email) {
        var restaurantProfile = retrieveRestaurantByEmailUseCase.execute(email);
        var restaurantOwner = retrieveRestaurantOwnerUseCase.execute(restaurantProfile.getId());
        return restaurantProfileDomainToResponseConverter.convert(restaurantProfile, restaurantOwner);
    }

    public void updateRestaurantProfile(RestaurantProfileRequest restaurantProfileRequest, Long id) {
        RestaurantProfile restaurantProfile = restaurantProfileRequestToDomainConverter.convert(restaurantProfileRequest);
        updateRestaurantProfileUseCase.execute(restaurantProfile, id);
    }

    public void delete(long id) {
        deleteRestaurantProfileUseCase.execute(id);
    }

    public void updateRestaurantOwner(RegisterRestaurantOwnerRequest registerRestaurantOwnerRequest, Long userId) {
        var restaurantOwner = restaurantOwnerRequestToDomainConverter.convert(registerRestaurantOwnerRequest);
        updateRestaurantOwnerUseCase.execute(restaurantOwner, userId);
    }
}