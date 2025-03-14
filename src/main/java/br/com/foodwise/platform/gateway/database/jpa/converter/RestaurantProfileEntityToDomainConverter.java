package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.gateway.database.jpa.entities.RestaurantProfileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantProfileEntityToDomainConverter {

    private final AddressEntityToDomainConverter addressEntityToDomainConverter;
    private final UserEntityToDomainConverter userEntityToDomainConverter;
    private final PhoneEntityToDomainConverter phoneEntityToDomainConverter;

    public RestaurantProfile convert(RestaurantProfileEntity source) {
        return new RestaurantProfile(
                source.getUserId(),
                source.getBusinessName(),
                source.getDescription(),
                source.getBusinessHours(),
                source.getDeliveryRadius(),
                source.getCuisineType(),
                source.isOpen(),
                source.isDeliveryOrder(),
                source.getCreatedAt(),
                source.getUpdatedAt(),
                userEntityToDomainConverter.convert(source.getUserEntity()),
                addressEntityToDomainConverter.convert(source.getAddressEntity()),
                phoneEntityToDomainConverter.convert(source.getPhoneEntity())
        );
    }
}
