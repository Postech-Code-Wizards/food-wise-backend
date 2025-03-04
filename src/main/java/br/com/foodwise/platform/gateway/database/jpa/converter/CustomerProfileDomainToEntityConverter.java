package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.CustomerProfile;
import br.com.foodwise.platform.gateway.database.jpa.entities.CustomerProfileEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerProfileDomainToEntityConverter {

    private final AddressDomainToEntityConverter addressDomainToEntityConverter;
    private final PhoneDomainToEntityConverter phoneDomainToEntityConverter;
    private final UserDomainToEntityConverter userDomainToEntityConverter;

    public CustomerProfileEntity convert(CustomerProfile source) {
        var customerProfileEntity = new CustomerProfileEntity();

        var mapper = new ModelMapper();
        mapper.map(source, customerProfileEntity);

        customerProfileEntity.setAddressEntity(addressDomainToEntityConverter
                .convert(source.getAddress()));
        customerProfileEntity
                .setPhoneEntity(phoneDomainToEntityConverter
                        .convert(source.getPhone()));
        customerProfileEntity.setUserEntity(userDomainToEntityConverter
                .convert(source.getUser()));

        return customerProfileEntity;
    }
}