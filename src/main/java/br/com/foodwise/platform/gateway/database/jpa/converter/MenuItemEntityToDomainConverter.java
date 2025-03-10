package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.gateway.database.jpa.entities.MenuItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuItemEntityToDomainConverter {

    private final MenuEntityToDomainConverter menuEntityToDomainConverter;

    public MenuItem convert(MenuItemEntity source) {
        return new MenuItem(
                source.getId(),
                source.getName(),
                source.getDescription(),
                source.getPrice(),
                source.getCategory(),
                source.isAvailable(),
                source.getImageUrl(),
                source.getCreatedAt(),
                source.getUpdatedAt(),
                menuEntityToDomainConverter.convert(source.getMenuEntity())
        );
    }
}
