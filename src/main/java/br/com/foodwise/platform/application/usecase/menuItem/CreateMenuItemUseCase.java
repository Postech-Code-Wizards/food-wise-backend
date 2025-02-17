package br.com.foodwise.platform.application.usecase.menuItem;

import br.com.foodwise.platform.domain.entities.MenuItem;
import br.com.foodwise.platform.domain.repository.MenuItemRepository;
import br.com.foodwise.platform.domain.repository.MenuRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateMenuItemUseCase {

    private final MenuItemRepository menuItemRepository;
    private final MenuRepository menuRepository;

    public MenuItem execute(MenuItem menuItem) {
        var menu = menuRepository.findById(menuItem.getMenu().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Menu"));
        menuItem.setMenu(menu);
        return menuItemRepository.save(menuItem);
    }
}
