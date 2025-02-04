package br.com.foodwise.platform.infrastructure.rest.converter.common;

import br.com.foodwise.platform.domain.entities.Address;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.AddressRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressRequestToEntityConverter implements Converter<AddressRequest, Address> {

    @Override
    public Address convert(AddressRequest source) {
        var address = new Address();

        var mapper = new ModelMapper();
        mapper.map(source, address);

        return address;
    }

}
