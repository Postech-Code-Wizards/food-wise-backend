package br.com.foodwise.platform.gateway.database.jpa;

import br.com.foodwise.platform.domain.RestaurantOwner;
import br.com.foodwise.platform.gateway.RestaurantOwnerGateway;
import br.com.foodwise.platform.gateway.database.jpa.converter.RestaurantOwnerDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.RestaurantOwnerEntityToDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.RestaurantOwnerEntity;
import br.com.foodwise.platform.gateway.database.jpa.repository.RestaurantOwnerRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RestaurantOwnerJpaGateway implements RestaurantOwnerGateway {

    private final RestaurantOwnerRepository restaurantOwnerRepository;
    private final RestaurantOwnerDomainToEntityConverter restaurantOwnerDomainToEntityConverter;
    private final RestaurantOwnerEntityToDomainConverter restaurantOwnerEntityToDomainConverter;

    @Override
    public RestaurantOwner findById(Long id) {

        RestaurantOwnerEntity restaurantOwnerEntity = restaurantOwnerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant owner "));
        return restaurantOwnerEntityToDomainConverter.convert(restaurantOwnerEntity);
    }

    @Override
    public void save(RestaurantOwner restaurantOwner) {
        log.info("Saving Restaurant Owner");
        log.info("Restaurant Owner: " + restaurantOwner.getFirstName() + " " + restaurantOwner.getLastName());
        log.info("user " + restaurantOwner.getUser().getEmail());
        RestaurantOwnerEntity restaurantOwnerEntity = restaurantOwnerDomainToEntityConverter.convert(restaurantOwner);
        log.info("Restaurant Owner: " + restaurantOwnerEntity.getFirstName() + " " + restaurantOwnerEntity.getLastName());
        log.info("user " + restaurantOwnerEntity.getUserEntity().getEmail());
        restaurantOwnerRepository.save(restaurantOwnerEntity);
    }
}
