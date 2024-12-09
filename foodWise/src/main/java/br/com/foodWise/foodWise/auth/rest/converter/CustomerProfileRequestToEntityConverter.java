package br.com.foodWise.foodWise.auth.rest.converter;

import br.com.foodWise.foodWise.auth.dtos.request.register.CustomerProfileRequest;
import br.com.foodWise.foodWise.model.entities.CustomerProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class CustomerProfileRequestToEntityConverter
        implements Converter<CustomerProfileRequest, CustomerProfile> {

    private final AddressRequestToEntityConverter addressRequestToEntityConverter;
    private final PhoneRequestToEntityConverter phoneRequestToEntityConverter;

    @Override
    public CustomerProfile convert(CustomerProfileRequest source) {
        var customerProfile = new CustomerProfile();

        customerProfile.setAddress(addressRequestToEntityConverter
                .convert(source.getAddress()));
        /*customerProfile
                .setPhone(phoneRequestToEntityConverter
                        .convert(source.getPhone()));*/
        customerProfile.setCreatedAt(ZonedDateTime.now());
        customerProfile.setUpdatedAt(ZonedDateTime.now());

        return customerProfile;
    }
}