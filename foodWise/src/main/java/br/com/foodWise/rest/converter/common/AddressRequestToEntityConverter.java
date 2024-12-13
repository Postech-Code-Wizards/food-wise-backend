package br.com.foodWise.rest.converter.common;

import br.com.foodWise.model.entities.Address;
import br.com.foodWise.rest.dtos.request.register.AddressRequest;
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
