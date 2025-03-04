package br.com.foodwise.platform.application.service;

import br.com.foodwise.platform.application.usecase.customer.*;
import br.com.foodwise.platform.application.usecase.user.DeleteUserUseCase;
import br.com.foodwise.platform.domain.CustomerProfile;
import br.com.foodwise.platform.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerProfileService {

    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateCustomerUserEmailUseCase updateCustomerUserEmailUseCase;
    private final DeleteCustomerProfileUseCase deleteCustomerProfileUseCase;
    private final RetrieveCustomerByEmailUseCase retrieveCustomerByEmailUseCase;
    private final RegisterCustomerUseCase registerCustomerUseCase;
    private final UpdateCustomerProfileUseCase updateCustomerProfileUseCase;

    public void registerCustomer(CustomerProfile customerProfile) {
        registerCustomerUseCase.execute(customerProfile);
    }

    public void updateCustomerProfile(CustomerProfile customerProfile, Long id) {
        updateCustomerProfileUseCase.execute(customerProfile, id);
    }

    public void updateCustomerUserEmail(User user, Long id) {
        updateCustomerUserEmailUseCase.execute(user, id);
    }

    public CustomerProfile retrieveCustomerByEmail() {
        return retrieveCustomerByEmailUseCase.execute();
    }

    public void delete(long id) {
        deleteCustomerProfileUseCase.execute(id);
    }

}
