package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.application.usecase.user.UpdateUserEmailUseCase;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.domain.enums.UserType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCustomerUserEmailUseCase {

    private final UpdateUserEmailUseCase updateUserEmailUseCase;

    @Transactional
    public void execute(User user, Long id) {
        updateUserEmailUseCase.execute(user, id, UserType.CUSTOMER);
    }

}
