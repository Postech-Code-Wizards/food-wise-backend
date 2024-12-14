package br.com.foodwise.rest.converter.restaurant;

import br.com.foodwise.model.entities.RestaurantProfile;
import br.com.foodwise.rest.dtos.response.RestaurantProfileResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantProfileEntityToResponseConverter implements Converter<RestaurantProfile, RestaurantProfileResponse> {
    @Override
    public RestaurantProfileResponse convert(RestaurantProfile source) {
        var response = new RestaurantProfileResponse();

        var mapper = new ModelMapper();
        mapper.map(source, response);

        return response;
    }
}
