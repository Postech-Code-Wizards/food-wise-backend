package br.com.foodWise.foodWise.auth.rest.converter;
import java.time.ZonedDateTime;

import br.com.foodWise.foodWise.auth.dtos.request.register.AddressRequest;
import br.com.foodWise.foodWise.model.entities.Address;

import org.springframework.core.convert.converter.Converter;

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
