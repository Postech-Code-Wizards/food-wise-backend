package br.com.foodWise.foodWise.rest.converter;

import br.com.foodWise.foodWise.model.entities.CustomerProfile;
import br.com.foodWise.foodWise.rest.dtos.response.CustomerProfileResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerProfileEntityToResponseConverter
        implements Converter<CustomerProfile, CustomerProfileResponse> {

    @Override
    public CustomerProfileResponse convert(CustomerProfile source) {
        var response = new CustomerProfileResponse();

        var mapper = new ModelMapper();
        mapper.map(source, response);

        return response;
    }
}
