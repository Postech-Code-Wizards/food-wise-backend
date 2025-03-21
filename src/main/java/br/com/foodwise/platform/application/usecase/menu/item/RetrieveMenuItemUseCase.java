package br.com.foodwise.platform.application.usecase.menu.item;

import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.gateway.MenuItemGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveMenuItemUseCase {

    private final MenuItemGateway menuItemGateway;

    public MenuItem execute(Long id) {
        return menuItemGateway.findById(id);
    }
}
