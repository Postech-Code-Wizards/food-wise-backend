package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.RestaurantOwner;
import br.com.foodwise.platform.gateway.database.jpa.entities.RestaurantOwnerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantOwnerEntityToDomainConverter {

    private final UserEntityToDomainConverter userEntityToDomainConverter;

    public RestaurantOwner convert(RestaurantOwnerEntity source) {
        return new RestaurantOwner(
                source.getId(),
                source.getFirstName(),
                source.getLastName(),
                source.getBusinessRegistrationNumber(),
                source.getBusinessEmail(),
                source.getCreatedAt(),
                source.getUpdatedAt(),
                userEntityToDomainConverter.convert(source.getUserEntity())
        );
    }
}
