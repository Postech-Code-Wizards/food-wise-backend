package br.com.foodWise.foodWise.rest.converter;

import br.com.foodWise.foodWise.model.entities.Address;
import br.com.foodWise.foodWise.rest.dtos.request.register.AddressRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class AddressRequestToEntityConverter implements Converter<AddressRequest, Address> {

    @Override
    public Address convert(AddressRequest source) {
        var address = new Address();
        address.setPostalCode(source.getPostalCode());
        address.setCountry(source.getCountry());
        address.setLatitude(source.getLatitude());
        address.setLongitude(source.getLongitude());
        address.setCreatedAt(ZonedDateTime.now());
        address.setUpdatedAt(ZonedDateTime.now());
        return address;
    }

}
