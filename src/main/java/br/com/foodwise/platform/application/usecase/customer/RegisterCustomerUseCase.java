package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.application.usecase.user.CreateUserUseCase;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.gateway.repository.CustomerProfileRepository;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.RegisterCustomerRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterCustomerUseCase {

    private final CreateUserUseCase createUserUseCase;
    private final CustomerProfileRepository customerProfileRepository;
    private final ConvertToCustomerProfileEntityUseCase convertToCustomerProfileEntity;

    @Transactional
    public void execute(RegisterCustomerRequest request) {
        var userRequest = request.getUser();
        var user = createUserUseCase.execute(userRequest.getEmail(), userRequest.getPassword(), UserType.CUSTOMER);

        var customerRequest = request.getCustomer();
        var newCustomer = convertToCustomerProfileEntity.execute(customerRequest);
        newCustomer.setUserEntity(user);
        customerProfileRepository.save(newCustomer);
    }

}
