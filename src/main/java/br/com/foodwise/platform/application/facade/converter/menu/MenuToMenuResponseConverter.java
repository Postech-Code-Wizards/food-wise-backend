package br.com.foodwise.platform.application.facade.converter.menu;

import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.application.facade.converter.restaurant.RestaurantProfileDomainToResponseConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuToMenuResponseConverter implements Converter<Menu, MenuResponse> {

    private final RestaurantProfileDomainToResponseConverter restaurantProfileDomainToResponseConverter;

    @Override
    public MenuResponse convert(Menu source) {
        var modelMapper = new ModelMapper();

        var menuResponse = new MenuResponse();
        modelMapper.map(source, menuResponse);

        var restaurantResponse = restaurantProfileDomainToResponseConverter
                .convert(source.getRestaurantProfile());

        menuResponse.setRestaurant(restaurantResponse);

        return menuResponse;
    }
}