package br.com.foodwise.platform.infrastructure.rest.converter.restaurant;

import br.com.foodwise.platform.domain.entities.RestaurantOwner;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RegisterRestaurantOwnerRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterRestaurantOwnerRequestToEntityConverter implements Converter<RegisterRestaurantOwnerRequest, RestaurantOwner> {

    @Override
    public RestaurantOwner convert(RegisterRestaurantOwnerRequest source) {
        var restaurantOwner = new RestaurantOwner();

        var mapper = new ModelMapper();
        mapper.map(source, restaurantOwner);
        return restaurantOwner;
    }
}
