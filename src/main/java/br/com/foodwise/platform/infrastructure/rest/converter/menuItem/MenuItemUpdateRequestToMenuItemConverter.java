package br.com.foodwise.platform.infrastructure.rest.converter.menuItem;

import br.com.foodwise.platform.domain.entities.MenuItem;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menuItem.RegisterMenuItemRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuItemUpdateRequestToMenuItemConverter {

    public MenuItem convert(MenuItem response, RegisterMenuItemRequest source) {
        var mapper = new ModelMapper();
        mapper.map(source, response);

        return response;
    }
}
