package br.com.foodwise.platform.application.facade;

import br.com.foodwise.platform.application.facade.converter.common.UserRequestToDomainConverter;
import br.com.foodwise.platform.application.facade.converter.customer.CustomerProfileDomainToResponseConverter;
import br.com.foodwise.platform.application.facade.converter.customer.CustomerProfileRequestToDomainConverter;
import br.com.foodwise.platform.application.usecase.customer.DeleteCustomerProfileUseCase;
import br.com.foodwise.platform.application.usecase.customer.RegisterCustomerUseCase;
import br.com.foodwise.platform.application.usecase.customer.RetrieveCustomerByEmailAuthenticatedUseCase;
import br.com.foodwise.platform.application.usecase.customer.RetrieveCustomerByEmailUseCase;
import br.com.foodwise.platform.application.usecase.customer.UpdateCustomerProfileUseCase;
import br.com.foodwise.platform.application.usecase.customer.UpdateCustomerUserEmailUseCase;
import br.com.foodwise.platform.application.usecase.user.DeleteUserUseCase;
import br.com.foodwise.platform.domain.CustomerProfile;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.CustomerProfileRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.RegisterCustomerRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.CustomerProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerProfileFacade {

    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateCustomerUserEmailUseCase updateCustomerUserEmailUseCase;
    private final DeleteCustomerProfileUseCase deleteCustomerProfileUseCase;
    private final RetrieveCustomerByEmailAuthenticatedUseCase retrieveCustomerByEmailAuthenticatedUseCase;
    private final RetrieveCustomerByEmailUseCase retrieveCustomerByEmailUseCase;
    private final RegisterCustomerUseCase registerCustomerUseCase;
    private final UpdateCustomerProfileUseCase updateCustomerProfileUseCase;
    private final CustomerProfileRequestToDomainConverter customerProfileRequestToDomainConverter;
    private final CustomerProfileDomainToResponseConverter customerProfileDomainToResponseConverter;
    private final UserRequestToDomainConverter userRequestToDomainConverter;

    public void registerCustomer(RegisterCustomerRequest registerCustomerRequest) {
        User user = userRequestToDomainConverter.convert(registerCustomerRequest.getUser());
        CustomerProfile customerProfile = customerProfileRequestToDomainConverter.convert(registerCustomerRequest.getCustomer(), user);
        registerCustomerUseCase.execute(customerProfile);
    }

    public void updateCustomerProfile(CustomerProfileRequest customerProfileRequest, Long id) {
        CustomerProfile customerProfile = customerProfileRequestToDomainConverter.convert(customerProfileRequest);
        updateCustomerProfileUseCase.execute(customerProfile, id);
    }

    public void updateCustomerUserEmail(UserRequest userRequest, Long id) {
        var user = userRequestToDomainConverter.convert(userRequest);
        updateCustomerUserEmailUseCase.execute(user, id);
    }

    public CustomerProfileResponse retrieveCustomerByEmail(String email) {
        var customerProfile = retrieveCustomerByEmailUseCase.execute(email);
        return customerProfileDomainToResponseConverter.convert(customerProfile);
    }

    public CustomerProfileResponse retrieveCustomerByEmailAuthenticated() {
        var customerProfile = retrieveCustomerByEmailAuthenticatedUseCase.execute();
        return customerProfileDomainToResponseConverter.convert(customerProfile);
    }

    public void delete(long id) {
        deleteCustomerProfileUseCase.execute(id);
    }

}
