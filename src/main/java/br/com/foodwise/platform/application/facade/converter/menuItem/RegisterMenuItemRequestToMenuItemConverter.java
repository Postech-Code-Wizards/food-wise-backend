package br.com.foodwise.platform.application.facade.converter.menuItem;

import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menuItem.RegisterMenuItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class RegisterMenuItemRequestToMenuItemConverter {

    public MenuItem convert(RegisterMenuItemRequest source, Menu menu) {
        return MenuItem.builder()
                .name(source.getName())
                .description(source.getDescription())
                .price(source.getPrice())
                .category(source.getCategory())
                .isAvailable(source.isAvailable())
                .imageUrl(source.getImageUrl())
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .menu(menu)
                .build();
    }
}
