package br.com.foodwise.platform.gateway.database.jpa;

import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.gateway.RestaurantProfileGateway;
import br.com.foodwise.platform.gateway.database.jpa.converter.RestaurantProfileDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.RestaurantProfileEntityToDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.RestaurantProfileEntity;
import br.com.foodwise.platform.gateway.database.jpa.repository.RestaurantProfileRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestaurantProfileJpaGateway implements RestaurantProfileGateway {

    private final RestaurantProfileRepository restaurantProfileRepository;
    private final RestaurantProfileDomainToEntityConverter restaurantProfileDomainToEntityConverter;
    private final RestaurantProfileEntityToDomainConverter restaurantProfileEntityToDomainConverter;

    @Override
    public RestaurantProfile findById(long id) {

        RestaurantProfileEntity restaurantProfileEntity = restaurantProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RESTAURANT_DOES_NOT_EXIST", ""));
        return restaurantProfileEntityToDomainConverter.convert(restaurantProfileEntity);
    }

    @Override
    public RestaurantProfile findByUserEntityEmail(String email) {

        RestaurantProfileEntity restaurantProfileEntity = restaurantProfileRepository.findByUserEntityEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant profile not found"));
        return restaurantProfileEntityToDomainConverter.convert(restaurantProfileEntity);
    }

    @Override
    public RestaurantProfile findByBusinessName(String businessName) {

        RestaurantProfileEntity restaurantProfileEntity = restaurantProfileRepository.findByBusinessName(businessName)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant " + businessName));
        return restaurantProfileEntityToDomainConverter.convert(restaurantProfileEntity);
    }

    @Override
    public RestaurantProfile save(RestaurantProfile restaurantProfile) {

        if(Objects.isNull(restaurantProfile)) {
            log.info("Restaurant profile is null");
        }

        RestaurantProfileEntity restaurantProfileEntity = restaurantProfileDomainToEntityConverter.convert(restaurantProfile);
        var restaurantSaved = restaurantProfileRepository.save(restaurantProfileEntity);
        return restaurantProfileEntityToDomainConverter.convert(restaurantSaved);
    }

}
