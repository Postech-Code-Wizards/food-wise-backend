package br.com.foodwise.platform.infrastructure.rest.converter.customer;

import br.com.foodwise.platform.gateway.entities.CustomerProfileEntity;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.CustomerProfileResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerProfileEntityToResponseConverter
        implements Converter<CustomerProfileEntity, CustomerProfileResponse> {

    @Override
    public CustomerProfileResponse convert(CustomerProfileEntity source) {
        var response = new CustomerProfileResponse();

        var mapper = new ModelMapper();
        mapper.map(source, response);

        return response;
    }
}
