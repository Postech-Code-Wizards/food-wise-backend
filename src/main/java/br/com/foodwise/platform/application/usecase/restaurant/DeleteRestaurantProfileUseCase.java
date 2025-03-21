package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.application.usecase.user.DeleteUserUseCase;
import br.com.foodwise.platform.domain.enums.UserType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteRestaurantProfileUseCase {

    private final DeleteUserUseCase deleteUserUseCase;

    @Transactional
    public void execute(long id) {
        deleteUserUseCase.execute(id, UserType.RESTAURANT_OWNER);
    }

}
