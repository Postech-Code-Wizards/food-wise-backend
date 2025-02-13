package br.com.foodwise.platform.infrastructure.rest.converter.menuItem;

import br.com.foodwise.platform.domain.entities.MenuItem;
import br.com.foodwise.platform.infrastructure.rest.converter.menu.RegisterMenuRequestToMenuConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.MenuItemResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuItemToMenuItemResponseConverter implements Converter<MenuItem, MenuItemResponse> {

    private final RegisterMenuRequestToMenuConverter registerMenuRequestToMenuConverter;

    @Override
    public MenuItemResponse convert(MenuItem source) {
        var modelMapper = new ModelMapper();

        var menuItemResponse = new MenuItemResponse();
        modelMapper.map(source, menuItemResponse);

        /*var menuResponse = registerMenuRequestToMenuConverter
                .convert(source.getMenu());*/

        /*menuItemResponse.setMenu(menuResponse);*/

        return menuItemResponse;
    }
}
