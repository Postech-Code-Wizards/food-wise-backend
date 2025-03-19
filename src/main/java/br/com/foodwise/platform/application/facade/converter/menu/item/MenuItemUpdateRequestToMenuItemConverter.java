package br.com.foodwise.platform.application.facade.converter.menu.item;

import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menu.item.RegisterMenuItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class MenuItemUpdateRequestToMenuItemConverter {

    public MenuItem convert(MenuItem menuItem, RegisterMenuItemRequest registerMenuItemRequest) {
        return MenuItem.builder()
                .id(menuItem.getId())
                .name(registerMenuItemRequest.getName())
                .description(registerMenuItemRequest.getDescription())
                .price(registerMenuItemRequest.getPrice())
                .category(registerMenuItemRequest.getCategory())
                .isAvailable(registerMenuItemRequest.isAvailable())
                .imageUrl(registerMenuItemRequest.getImageUrl())
                .createdAt(menuItem.getCreatedAt())
                .updatedAt(ZonedDateTime.now())
                .menu(registerMenuItemRequest.getMenu())
                .build();
    }
}
