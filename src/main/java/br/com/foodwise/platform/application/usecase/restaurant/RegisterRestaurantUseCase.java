package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.application.usecase.user.CreateUserUseCase;
import br.com.foodwise.platform.domain.entities.enums.UserType;
import br.com.foodwise.platform.domain.repository.RestaurantProfileRepository;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RegisterRestaurantRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterRestaurantUseCase {

    private final CreateUserUseCase createUserUseCase;
    private final RestaurantProfileRepository restaurantProfileRepository;
    private final ConvertToRestaurantProfileEntityUseCase convertToRestaurantProfileEntityUseCase;

    @Transactional
    public void execute(RegisterRestaurantRequest request) {
        var userRequest = request.getUser();
        var user = createUserUseCase.execute(userRequest.getEmail(), userRequest.getPassword(), UserType.RESTAURANT_OWNER);

        var restaurantRequest = request.getRestaurant();
        var newRestaurant = convertToRestaurantProfileEntityUseCase.execute(restaurantRequest);
        newRestaurant.setUser(user);
        restaurantProfileRepository.save(newRestaurant);
    }

}
