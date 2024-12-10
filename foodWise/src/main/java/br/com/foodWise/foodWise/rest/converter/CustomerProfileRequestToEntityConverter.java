package br.com.foodWise.foodWise.rest.converter;

import br.com.foodWise.foodWise.model.entities.CustomerProfile;
import br.com.foodWise.foodWise.rest.dtos.request.register.CustomerProfileRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerProfileRequestToEntityConverter
        implements Converter<CustomerProfileRequest, CustomerProfile> {

    private final AddressRequestToEntityConverter addressRequestToEntityConverter;
    private final PhoneRequestToEntityConverter phoneRequestToEntityConverter;

    @Override
    public CustomerProfile convert(CustomerProfileRequest source) {
        var customerProfile = new CustomerProfile();

        var mapper = new ModelMapper();
        mapper.map(source, customerProfile);

        customerProfile.setAddress(addressRequestToEntityConverter
                .convert(source.getAddress()));
        customerProfile
                .setPhone(phoneRequestToEntityConverter
                        .convert(source.getPhone()));

        return customerProfile;
    }
}