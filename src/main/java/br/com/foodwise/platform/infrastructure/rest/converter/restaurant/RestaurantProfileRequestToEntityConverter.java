package br.com.foodwise.platform.infrastructure.rest.converter.restaurant;

import br.com.foodwise.platform.gateway.entities.RestaurantProfileEntity;
import br.com.foodwise.platform.infrastructure.rest.converter.common.AddressRequestToEntityConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.common.PhoneRequestToEntityConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantProfileRequestToEntityConverter implements Converter<RestaurantProfileRequest, RestaurantProfileEntity> {
    private final AddressRequestToEntityConverter addressRequestToEntityConverter;
    private final PhoneRequestToEntityConverter phoneRequestToEntityConverter;

    @Override
    public RestaurantProfileEntity convert(RestaurantProfileRequest source) {
        var restaurantProfile = new RestaurantProfileEntity();

        var mapper = new ModelMapper();
        mapper.map(source, restaurantProfile);

        restaurantProfile.setAddressEntity(addressRequestToEntityConverter
                .convert(source.getAddress()));
        restaurantProfile
                .setPhoneEntity(phoneRequestToEntityConverter
                        .convert(source.getPhone()));

        return restaurantProfile;
    }
}
