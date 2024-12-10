package br.com.foodWise.foodWise.rest.converter.restaurant;

import br.com.foodWise.foodWise.model.entities.RestaurantProfile;
import br.com.foodWise.foodWise.rest.dtos.request.register.RestaurantProfileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantProfileRequestToEntityConverter implements Converter<RestaurantProfileRequest, RestaurantProfile> {
    @Override
    public RestaurantProfile convert(RestaurantProfileRequest source) {
        return null;
    }
}
