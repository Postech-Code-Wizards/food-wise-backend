package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.application.usecase.user.UpdateUserEmailUseCase;
import br.com.foodwise.platform.domain.entities.enums.UserType;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateRestaurantUserEmailUseCase {

    private final UpdateUserEmailUseCase updateUserEmailUseCase;

    @Transactional
    public void execute(UserRequest userRequest, Long id){
        updateUserEmailUseCase.execute(userRequest, id, UserType.RESTAURANT_OWNER);
    }

}
