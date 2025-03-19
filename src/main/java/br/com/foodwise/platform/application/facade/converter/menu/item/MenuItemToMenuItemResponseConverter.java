package br.com.foodwise.platform.application.facade.converter.menu.item;

import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.MenuItemResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuItemToMenuItemResponseConverter implements Converter<MenuItem, MenuItemResponse> {

    @Override
    public MenuItemResponse convert(MenuItem source) {
        var modelMapper = new ModelMapper();

        var menuItemResponse = new MenuItemResponse();
        modelMapper.map(source, menuItemResponse);

        return menuItemResponse;
    }
}
