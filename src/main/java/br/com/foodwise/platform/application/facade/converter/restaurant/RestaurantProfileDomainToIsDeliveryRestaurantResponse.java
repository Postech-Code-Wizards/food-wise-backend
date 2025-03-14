package br.com.foodwise.platform.application.facade.converter.restaurant;

import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.IsDeliveryRestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class RestaurantProfileDomainToIsDeliveryRestaurantResponse implements Converter<RestaurantProfile, IsDeliveryRestaurantResponse> {

    @Override
    public IsDeliveryRestaurantResponse convert(RestaurantProfile source) {
       var response = new IsDeliveryRestaurantResponse();

       var mapper = new ModelMapper();

       mapper.map(source, response);

        return response;
    }
}
