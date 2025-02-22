package br.com.foodwise.platform.application.service;

import br.com.foodwise.platform.application.usecase.customer.*;
import br.com.foodwise.platform.application.usecase.user.DeleteUserUseCase;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.CustomerProfileRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.RegisterCustomerRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.CustomerProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class CustomerProfileService {

    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateCustomerUserEmailUseCase updateCustomerUserEmailUseCase;
    private final DeleteCustomerProfileUseCase deleteCustomerProfileUseCase;
    private final RetrieveCustomerByEmailUseCase retrieveCustomerByEmailUseCase;
    private final RegisterCustomerUseCase registerCustomerUseCase;
    private final UpdateCustomerProfileUseCase updateCustomerProfileUseCase;

    public void registerCustomer(RegisterCustomerRequest request) {
        registerCustomerUseCase.execute(request);
    }

    public void updateCustomerProfile(CustomerProfileRequest customerProfileRequest, Long id) {
        updateCustomerProfileUseCase.execute(customerProfileRequest, id);
    }

    public void updateCustomerUserEmail(UserRequest userRequest, Long id) {
        updateCustomerUserEmailUseCase.execute(userRequest, id);
    }

    public CustomerProfileResponse retrieveCustomerByEmail(@RequestParam String email) {
        return retrieveCustomerByEmailUseCase.execute(email);
    }

    public void delete(long id) {
        deleteCustomerProfileUseCase.execute(id);
    }

}
