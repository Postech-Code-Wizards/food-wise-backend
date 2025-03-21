package br.com.foodwise.platform.application.facade.converter.customer;

import br.com.foodwise.platform.application.facade.converter.common.AddressRequestToDomainConverter;
import br.com.foodwise.platform.application.facade.converter.common.PhoneRequestToDomainConverter;
import br.com.foodwise.platform.domain.CustomerProfile;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.CustomerProfileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerProfileRequestToDomainConverter {

    private final AddressRequestToDomainConverter addressRequestToDomainConverter;
    private final PhoneRequestToDomainConverter phoneRequestToDomainConverter;

    public CustomerProfile convert(CustomerProfileRequest source, User user) {
        return new CustomerProfile(
                null,
                source.getFirstName(),
                source.getLastName(),
                addressRequestToDomainConverter.convert(source.getAddress()),
                null,
                null,
                user,
                phoneRequestToDomainConverter.convert(source.getPhone())
        );
    }

    public CustomerProfile convert(CustomerProfileRequest source) {
        return new CustomerProfile(
                null,
                source.getFirstName(),
                source.getLastName(),
                addressRequestToDomainConverter.convert(source.getAddress()),
                null,
                null,
                null,
                phoneRequestToDomainConverter.convert(source.getPhone())
        );
    }
}
