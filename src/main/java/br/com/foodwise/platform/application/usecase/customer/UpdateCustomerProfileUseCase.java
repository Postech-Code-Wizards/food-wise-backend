package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.gateway.repository.CustomerProfileRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.CustomerProfileRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class UpdateCustomerProfileUseCase {

    private final CustomerProfileRepository customerProfileRepository;
    private final ConvertToCustomerProfileEntityUseCase convertToCustomerProfileEntity;

    @Transactional
    public void execute(CustomerProfileRequest customerProfileRequest, Long id) {
        var existingCustomer = customerProfileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CUSTOMER_DOES_NOT_EXIST", ""));

        var customerProfile = convertToCustomerProfileEntity.execute(customerProfileRequest);

        existingCustomer.setFirstName(customerProfile.getFirstName());
        existingCustomer.setLastName(customerProfile.getLastName());
        existingCustomer.setAddressEntity(customerProfile.getAddressEntity());
        existingCustomer.setUpdatedAt(ZonedDateTime.now());
        existingCustomer.setPhoneEntity(customerProfile.getPhoneEntity());

        customerProfileRepository.save(existingCustomer);
    }

}
