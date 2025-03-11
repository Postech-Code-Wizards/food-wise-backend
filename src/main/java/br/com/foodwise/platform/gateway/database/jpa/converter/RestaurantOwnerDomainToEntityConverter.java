package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.RestaurantOwner;
import br.com.foodwise.platform.gateway.database.jpa.entities.RestaurantOwnerEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantOwnerDomainToEntityConverter {

    public RestaurantOwnerEntity convert(RestaurantOwner source) {
        var mapper = new ModelMapper();
        return mapper.map(source, RestaurantOwnerEntity.class);
    }
}
