package br.com.foodwise.platform.application.facade.converter.restaurant;

import br.com.foodwise.platform.domain.RestaurantOwner;
import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantProfileDomainToResponseConverter {

    public RestaurantProfileResponse convert(RestaurantProfile source) {
        var response = new RestaurantProfileResponse();

        var mapper = new ModelMapper();
        mapper.map(source, response);

        return response;
    }

    public RestaurantProfileResponse convert(RestaurantProfile source, RestaurantOwner owner) {
        var response = new RestaurantProfileResponse();

        var mapper = new ModelMapper();
        mapper.map(source, response);

        response.setRestaurantOwner(owner);

        return response;
    }
}
