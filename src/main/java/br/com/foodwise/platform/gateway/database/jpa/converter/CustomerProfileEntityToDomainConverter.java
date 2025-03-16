package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.CustomerProfile;
import br.com.foodwise.platform.gateway.database.jpa.entities.CustomerProfileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerProfileEntityToDomainConverter {

    private final AddressEntityToDomainConverter addressEntityToDomainConverter;
    private final UserEntityToDomainConverter userEntityToDomainConverter;
    private final PhoneEntityToDomainConverter phoneEntityToDomainConverter;

    public CustomerProfile convert(CustomerProfileEntity source) {
        return new CustomerProfile(
                source.getUserId(),
                source.getFirstName(),
                source.getLastName(),
                addressEntityToDomainConverter.convert(source.getAddressEntity()),
                source.getCreatedAt(),
                source.getUpdatedAt(),
                userEntityToDomainConverter.convert(source.getUserEntity()),
                phoneEntityToDomainConverter.convert(source.getPhoneEntity())
        );
    }
}
