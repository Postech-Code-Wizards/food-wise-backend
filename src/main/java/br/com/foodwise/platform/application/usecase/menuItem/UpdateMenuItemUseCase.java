package br.com.foodwise.platform.application.usecase.menuItem;

import br.com.foodwise.platform.domain.entities.MenuItem;
import br.com.foodwise.platform.domain.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateMenuItemUseCase {
    private final MenuItemRepository menuItemRepository;

    public MenuItem execute(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }
}
