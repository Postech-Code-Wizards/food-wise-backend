package br.com.foodwise.platform.infrastructure.rest.converter.menuItem;

import br.com.foodwise.platform.domain.entities.MenuItem;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menuItem.RegisterMenuItemRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuItemUpdateRequestToMenuItemConverter {

    public void convert(RegisterMenuItemRequest source, MenuItem target) {
        var modelMapper = new ModelMapper();
        modelMapper.map(source, target);
    }
}
