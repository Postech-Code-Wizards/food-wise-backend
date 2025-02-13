package br.com.foodwise.platform.application.usecase.menuItem;

import br.com.foodwise.platform.domain.entities.MenuItem;
import br.com.foodwise.platform.domain.repository.MenuItemRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveMenuItemUseCase {

    private final MenuItemRepository menuItemRepository;

    public MenuItem execute(Long id) {
        return menuItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Menu Item"));
    }
}
