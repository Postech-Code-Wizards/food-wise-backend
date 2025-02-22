package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.domain.repository.CustomerProfileRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.CustomerProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class RetrieveCustomerByEmailUseCase {

    private final CustomerProfileRepository customerProfileRepository;
    private final ConvertToCustomerProfileResponseUseCase convertToCustomerProfileResponse;

    public CustomerProfileResponse execute(@RequestParam String email) {
        var customerProfile = customerProfileRepository
                .findByUserEmail(email).orElseThrow(() -> new ResourceNotFoundException("Usuário " + email));
        return convertToCustomerProfileResponse.execute(customerProfile);
    }

}
