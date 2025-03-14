package br.com.foodwise.platform.application.facade.converter.restaurant;

import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.application.facade.converter.common.AddressRequestToDomainConverter;
import br.com.foodwise.platform.application.facade.converter.common.PhoneRequestToDomainConverter;
import br.com.foodwise.platform.application.facade.converter.common.UserRequestToDomainConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantProfileRequestToDomainConverter implements Converter<RestaurantProfileRequest, RestaurantProfile> {

    private final AddressRequestToDomainConverter addressRequestToDomainConverter;
    private final PhoneRequestToDomainConverter phoneRequestToDomainConverter;
    private final UserRequestToDomainConverter userRequestToDomainConverter;

    public RestaurantProfile convert(RestaurantProfileRequest source, User user) {
        return new RestaurantProfile(
                null,
                source.getBusinessName(),
                source.getDescription(),
                source.getBusinessHours(),
                source.getDeliveryRadius(),
                source.getCuisineType(),
                true,
                false,
                null,
                user,
                addressRequestToDomainConverter.convert(source.getAddress()),
                phoneRequestToDomainConverter.convert(source.getPhone())
        );
    }

    public RestaurantProfile convert(RestaurantProfileRequest source) {

        return new RestaurantProfile(
                null,
                source.getBusinessName(),
                source.getDescription(),
                source.getBusinessHours(),
                source.getDeliveryRadius(),
                source.getCuisineType(),
                true,
                false,
                null,
                null,
                addressRequestToDomainConverter.convert(source.getAddress()),
                phoneRequestToDomainConverter.convert(source.getPhone())
        );
    }
}
