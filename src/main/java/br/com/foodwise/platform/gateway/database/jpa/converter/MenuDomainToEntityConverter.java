package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.gateway.database.jpa.entities.MenuEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuDomainToEntityConverter {

    private final RestaurantProfileDomainToEntityConverter restaurantProfileDomainToEntityConverter;

    public MenuEntity convert(Menu source) {

        var menuEntity = new MenuEntity();

        var modelMapper = new ModelMapper();
        modelMapper.map(source, menuEntity);

        menuEntity.setRestaurantProfileEntity(
                restaurantProfileDomainToEntityConverter.convert(source.getRestaurantProfile()));

        return menuEntity;
    }
}