package br.com.foodwise.platform.gateway.database.jpa;

import br.com.foodwise.platform.application.usecase.user.FindUserByIdUseCase;
import br.com.foodwise.platform.domain.RestaurantOwner;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.gateway.RestaurantOwnerGateway;
import br.com.foodwise.platform.gateway.UserGateway;
import br.com.foodwise.platform.gateway.database.jpa.converter.RestaurantOwnerDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.RestaurantOwnerEntityToDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.UserDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.RestaurantOwnerEntity;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import br.com.foodwise.platform.gateway.database.jpa.repository.RestaurantOwnerRepository;
import br.com.foodwise.platform.gateway.database.jpa.repository.UserRepository;
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
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final UserDomainToEntityConverter userDomainToEntityConverter;
    private final UserRepository userRepository;
    private final UserGateway userGateway;

    @Override
    public RestaurantOwner findById(Long id) {

        RestaurantOwnerEntity restaurantOwnerEntity = restaurantOwnerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant owner "));
        return restaurantOwnerEntityToDomainConverter.convert(restaurantOwnerEntity);
    }

    @Override
    public void save(RestaurantOwner restaurantOwner) {
        /*User user = findUserByEmailUseCase.findUserByEmail(restaurantOwner.getUser().getEmail());*/
        /*User userFind = findUserByIdUseCase.findUserById(restaurantOwner.getUser().getId());
        UserEntity userEntity = userDomainToEntityConverter.convert(userFind);*/

        /*UserEntity user = userRepository.findById(restaurantOwner.getUser().getId()).orElseThrow(() -> new ResourceNotFoundException("User"));*/

        User user = userGateway.findUserById(restaurantOwner.getUser().getId());
        UserEntity userEntity = userDomainToEntityConverter.convert(user);


        RestaurantOwnerEntity restaurantOwnerEntity = restaurantOwnerDomainToEntityConverter.convert(restaurantOwner);
        restaurantOwnerEntity.setUserEntity(userEntity);

        restaurantOwnerRepository.save(restaurantOwnerEntity);
    }
}
