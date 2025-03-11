package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.gateway.database.jpa.entities.MenuItemEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuItemDomainToEntityConverter {

    private final MenuDomainToEntityConverter menuDomainToEntityConverter;

    public MenuItemEntity convert(MenuItem source) {

        var menuItemEntity = new MenuItemEntity();

        var modelMapper = new ModelMapper();
        modelMapper.map(source, menuItemEntity);

        menuItemEntity.setMenuEntity(
                menuDomainToEntityConverter.convert(source.getMenu()));

        return menuItemEntity;
    }
}