package br.com.foodwise.platform.rest.converter.customer;

import br.com.foodwise.platform.model.entities.CustomerProfile;
import br.com.foodwise.platform.rest.dtos.response.CustomerProfileResponse;
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
