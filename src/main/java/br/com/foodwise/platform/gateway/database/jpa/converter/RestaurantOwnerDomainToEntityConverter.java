package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.RestaurantOwner;
import br.com.foodwise.platform.gateway.database.jpa.entities.RestaurantOwnerEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RestaurantOwnerDomainToEntityConverter {

    private final UserDomainToEntityConverter userDomainToEntityConverter;

    public RestaurantOwnerEntity convert(RestaurantOwner source) {

        var restaurantOwnerEntity = new RestaurantOwnerEntity();

        var mapper = new ModelMapper();
        mapper.map(source, restaurantOwnerEntity);

        restaurantOwnerEntity.setUserEntity(
                userDomainToEntityConverter.convert(source.getUser())
        );

        return restaurantOwnerEntity;
    }
}
