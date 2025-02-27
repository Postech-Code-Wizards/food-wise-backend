package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.application.usecase.user.UpdateUserEmailUseCase;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCustomerUserEmailUseCase {

    private final UpdateUserEmailUseCase updateUserEmailUseCase;

    @Transactional
    public void execute(UserRequest userRequest, Long id) {
        updateUserEmailUseCase.execute(userRequest, id, UserType.CUSTOMER);
    }

}
