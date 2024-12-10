package br.com.foodWise.foodWise.rest.converter.restaurant;

import br.com.foodWise.foodWise.model.entities.RestaurantProfile;
import br.com.foodWise.foodWise.rest.converter.common.AddressRequestToEntityConverter;
import br.com.foodWise.foodWise.rest.converter.common.PhoneRequestToEntityConverter;
import br.com.foodWise.foodWise.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantProfileRequestToEntityConverter implements Converter<RestaurantProfileRequest, RestaurantProfile> {
    private final AddressRequestToEntityConverter addressRequestToEntityConverter;
    private final PhoneRequestToEntityConverter phoneRequestToEntityConverter;

    @Override
    public RestaurantProfile convert(RestaurantProfileRequest source) {
        var restaurantProfile = new RestaurantProfile();

        var mapper = new ModelMapper();
        mapper.map(source, restaurantProfile);

        restaurantProfile.setAddress(addressRequestToEntityConverter
                .convert(source.getAddress()));
        restaurantProfile
                .setPhone(phoneRequestToEntityConverter
                        .convert(source.getPhone()));

        return restaurantProfile;
    }
}
