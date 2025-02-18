package br.com.foodwise.platform.infrastructure.rest.converter.menuItem;

import br.com.foodwise.platform.domain.entities.MenuItem;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menuItem.RegisterMenuItemRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterMenuItemRequestToMenuItemConverter implements Converter<RegisterMenuItemRequest, MenuItem> {

    @Override
    public MenuItem convert(RegisterMenuItemRequest source) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(source, MenuItem.class);
    }
}
