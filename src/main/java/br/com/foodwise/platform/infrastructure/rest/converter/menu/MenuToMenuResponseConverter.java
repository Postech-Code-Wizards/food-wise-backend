package br.com.foodwise.platform.infrastructure.rest.converter.menu;

import br.com.foodwise.platform.domain.entities.Menu;
import br.com.foodwise.platform.infrastructure.rest.converter.restaurant.RestaurantProfileEntityToResponseConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuToMenuResponseConverter implements Converter<Menu, MenuResponse> {

    private final RestaurantProfileEntityToResponseConverter restaurantProfileEntityToResponseConverter;

    @Override
    public MenuResponse convert(Menu source) {
        var modelMapper = new ModelMapper();

        var menuResponse = new MenuResponse();
        modelMapper.map(source, menuResponse);

        var restaurantResponse = restaurantProfileEntityToResponseConverter
                .convert(source.getRestaurantProfile());

        menuResponse.setRestaurant(restaurantResponse);

        return menuResponse;
    }
}