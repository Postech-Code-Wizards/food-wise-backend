package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.gateway.database.jpa.entities.MenuEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuEntityToDomainConverter {

    private final RestaurantProfileEntityToDomainConverter restaurantProfileEntityToDomainConverter;

    public Menu convert(MenuEntity source) {
        return new Menu(
                source.getId(),
                restaurantProfileEntityToDomainConverter.convert(source.getRestaurantProfileEntity()),
                source.getName(),
                source.getDescription(),
                source.getCreatedAt(),
                source.getUpdatedAt()
        );
    }
}
