package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.gateway.database.jpa.entities.RestaurantProfileEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantProfileDomainToEntityConverter {

    private final AddressDomainToEntityConverter addressDomainToEntityConverter;
    private final PhoneDomainToEntityConverter phoneDomainToEntityConverter;
    private final UserDomainToEntityConverter userDomainToEntityConverter;

    public RestaurantProfileEntity convert(RestaurantProfile source) {
        var restaurantProfileEntity = new RestaurantProfileEntity();

        var mapper = new ModelMapper();
        mapper.map(source, restaurantProfileEntity);

        restaurantProfileEntity.setAddressEntity(addressDomainToEntityConverter
                .convert(source.getAddress()));
        restaurantProfileEntity
                .setPhoneEntity(phoneDomainToEntityConverter
                        .convert(source.getPhone()));
        restaurantProfileEntity.setUserEntity(userDomainToEntityConverter
                .convert(source.getUser()));

        return restaurantProfileEntity;
    }
}