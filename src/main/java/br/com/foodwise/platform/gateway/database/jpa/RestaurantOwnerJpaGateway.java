package br.com.foodwise.platform.gateway.database.jpa;

import br.com.foodwise.platform.domain.RestaurantOwner;
import br.com.foodwise.platform.gateway.RestaurantOwnerGateway;
import br.com.foodwise.platform.gateway.database.jpa.converter.RestaurantOwnerDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.RestaurantOwnerEntityToDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.UserDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.RestaurantOwnerEntity;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import br.com.foodwise.platform.gateway.database.jpa.repository.RestaurantOwnerRepository;
import br.com.foodwise.platform.gateway.database.jpa.repository.UserRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantOwnerJpaGateway implements RestaurantOwnerGateway {

    private final RestaurantOwnerRepository restaurantOwnerRepository;
    private final RestaurantOwnerDomainToEntityConverter restaurantOwnerDomainToEntityConverter;
    private final RestaurantOwnerEntityToDomainConverter restaurantOwnerEntityToDomainConverter;
    private final UserRepository userRepository;

    private final UserDomainToEntityConverter userDomainToEntityConverter;

    @Override
    public RestaurantOwner findById(Long id) {

        RestaurantOwnerEntity restaurantOwnerEntity = restaurantOwnerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant owner "));
        return restaurantOwnerEntityToDomainConverter.convert(restaurantOwnerEntity);
    }

    @Override
    public void save(RestaurantOwner restaurantOwner) {
        UserEntity userEntity = userRepository.findById(restaurantOwner.getUser().getId()).orElseThrow(() -> new ResourceNotFoundException("User"));

        RestaurantOwnerEntity restaurantOwnerEntity = restaurantOwnerDomainToEntityConverter.convert(restaurantOwner);
        restaurantOwnerEntity.setUserEntity(userEntity);

        restaurantOwnerRepository.save(restaurantOwnerEntity);
    }

}
