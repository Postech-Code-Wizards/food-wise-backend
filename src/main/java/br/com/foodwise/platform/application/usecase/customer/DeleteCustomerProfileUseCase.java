package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.application.usecase.user.DeleteUserUseCase;
import br.com.foodwise.platform.domain.entities.enums.UserType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCustomerProfileUseCase {

    private final DeleteUserUseCase deleteUserUseCase;

    @Transactional
    public void execute(long id) {
        deleteUserUseCase.execute(id, UserType.CUSTOMER);
    }

}