package br.com.foodwise.platform.rest.converter.customer;

import br.com.foodwise.platform.model.entities.CustomerProfile;
import br.com.foodwise.platform.rest.converter.common.AddressRequestToEntityConverter;
import br.com.foodwise.platform.rest.converter.common.PhoneRequestToEntityConverter;
import br.com.foodwise.platform.rest.converter.common.UserRequestToEntityConverter;
import br.com.foodwise.platform.rest.dtos.request.register.customer.CustomerProfileRequest;
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
    private final UserRequestToEntityConverter userRequestToEntityConverter;

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

        customerProfile
                .setUser(userRequestToEntityConverter
                        .convert(source.getUserRequest()));

        return customerProfile;
    }
}
