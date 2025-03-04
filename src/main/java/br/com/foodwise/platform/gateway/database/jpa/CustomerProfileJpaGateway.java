package br.com.foodwise.platform.gateway.database.jpa;

import br.com.foodwise.platform.domain.CustomerProfile;
import br.com.foodwise.platform.gateway.CustomerProfileGateway;
import br.com.foodwise.platform.gateway.database.jpa.converter.CustomerProfileDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.CustomerDomainProfileEntityToDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.CustomerProfileEntity;
import br.com.foodwise.platform.gateway.database.jpa.repository.CustomerProfileRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerProfileJpaGateway implements CustomerProfileGateway {

    private final CustomerProfileRepository customerProfileRepository;
    private final CustomerDomainProfileEntityToDomainConverter customerDomainProfileEntityToDomainConverter;
    private final CustomerProfileDomainToEntityConverter customerProfileDomainToEntityConverter;

    @Override
    public CustomerProfile findById(long id) {
        var customerProfileEntity = customerProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CUSTOMER_DOES_NOT_EXIST", ""));

        return customerDomainProfileEntityToDomainConverter.convert(customerProfileEntity);
    }

    @Override
    public CustomerProfile findByUserEmail(String email) {
        var customerProfileEntity = customerProfileRepository.findByUserEntityEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User " + email));

        return customerDomainProfileEntityToDomainConverter.convert(customerProfileEntity);
    }

    @Override
    public void save(CustomerProfile customerProfile) {
        if(Objects.isNull(customerProfile)) {
            log.info("Customer profile is null");
        }

        CustomerProfileEntity customerProfileEntity = customerProfileDomainToEntityConverter.convert(customerProfile);
        customerProfileRepository.save(customerProfileEntity);

    }

}
