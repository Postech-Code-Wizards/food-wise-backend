package br.com.foodwise.platform.infrastructure.rest.converter.customer;

import br.com.foodwise.platform.gateway.entities.CustomerProfileEntity;
import br.com.foodwise.platform.infrastructure.rest.converter.common.AddressRequestToEntityConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.common.PhoneRequestToEntityConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.CustomerProfileRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerProfileRequestToEntityConverter
        implements Converter<CustomerProfileRequest, CustomerProfileEntity> {

    private final AddressRequestToEntityConverter addressRequestToEntityConverter;
    private final PhoneRequestToEntityConverter phoneRequestToEntityConverter;

    @Override
    public CustomerProfileEntity convert(CustomerProfileRequest source) {
        var customerProfile = new CustomerProfileEntity();

        var mapper = new ModelMapper();
        mapper.map(source, customerProfile);

        customerProfile.setAddressEntity(addressRequestToEntityConverter
                .convert(source.getAddress()));
        customerProfile
                .setPhoneEntity(phoneRequestToEntityConverter
                        .convert(source.getPhone()));

        return customerProfile;
    }
}
