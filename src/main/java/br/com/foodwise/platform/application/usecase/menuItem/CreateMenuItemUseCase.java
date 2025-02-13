package br.com.foodwise.platform.application.usecase.menuItem;

import br.com.foodwise.platform.domain.entities.MenuItem;
import br.com.foodwise.platform.domain.repository.MenuItemRepository;
import br.com.foodwise.platform.domain.repository.MenuRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateMenuItemUseCase {

    private final MenuItemRepository menuItemRepository;
    private final MenuRepository menuRepository;

    public MenuItem execute(MenuItem menuItem) {
        log.info("Creating menu item: {}", menuItem);
        log.info("Menu id: {}", menuItem.getMenu().getId());
        log.info("Name id: {}", menuItem.getName());
        log.info("Description id: {}", menuItem.getDescription());
        log.info("price id: {}", menuItem.getPrice());
        log.info("category id: {}", menuItem.getCategory());
        log.info("imageurl id: {}", menuItem.getImageUrl());
        var menu = menuRepository.findById(menuItem.getMenu().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Menu"));
        menuItem.setMenu(menu);
        return menuItemRepository.save(menuItem);
    }
}
