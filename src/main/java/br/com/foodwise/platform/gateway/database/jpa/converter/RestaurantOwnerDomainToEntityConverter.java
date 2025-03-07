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

        log.info("Restaurant Domain");
        log.info("Restaurant Owner: {} {}", source.getFirstName(), source.getLastName());
        log.info("user {}", source.getUser().getEmail());
        log.info("user {}", source.getUser().getId());
        var restaurantOwnerEntity = new RestaurantOwnerEntity();

        var mapper = new ModelMapper();
        mapper.map(source, restaurantOwnerEntity);



        restaurantOwnerEntity.setUserEntity(
                userDomainToEntityConverter.convert(source.getUser())
        );

        log.info("Restaurant Entity");
        log.info("Restaurant Owner: {} {}", restaurantOwnerEntity.getFirstName(), restaurantOwnerEntity.getLastName());
        log.info("user {}", restaurantOwnerEntity.getUserEntity().getEmail());

        return restaurantOwnerEntity;
    }
}
