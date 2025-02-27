package br.com.foodwise.platform.infrastructure.rest.converter.restaurant;

import br.com.foodwise.platform.gateway.entities.RestaurantProfileEntity;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantProfileEntityToResponseConverter implements Converter<RestaurantProfileEntity, RestaurantProfileResponse> {
    @Override
    public RestaurantProfileResponse convert(RestaurantProfileEntity source) {
        var response = new RestaurantProfileResponse();

        var mapper = new ModelMapper();
        mapper.map(source, response);

        return response;
    }
}
