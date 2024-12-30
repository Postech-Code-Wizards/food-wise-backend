package br.com.foodwise.platform.rest.converter.restaurant;

import br.com.foodwise.platform.model.entities.RestaurantProfile;
import br.com.foodwise.platform.rest.converter.common.UserRequestToEntityConverter;
import br.com.foodwise.platform.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import br.com.foodwise.platform.rest.converter.common.AddressRequestToEntityConverter;
import br.com.foodwise.platform.rest.converter.common.PhoneRequestToEntityConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantProfileRequestToEntityConverter implements Converter<RestaurantProfileRequest, RestaurantProfile> {
    private final AddressRequestToEntityConverter addressRequestToEntityConverter;
    private final PhoneRequestToEntityConverter phoneRequestToEntityConverter;
    private final UserRequestToEntityConverter userRequestToEntityConverter;

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
        restaurantProfile
                .setUser(userRequestToEntityConverter
                        .convert(source.getUserRequest()));

        return restaurantProfile;
    }
}
