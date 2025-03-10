package br.com.foodwise.platform.application.usecase.menuItem;

import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.gateway.MenuGateway;
import br.com.foodwise.platform.gateway.MenuItemGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateMenuItemUseCase {

    private final MenuItemGateway menuItemGateway;
    private final MenuGateway menuGateway;

    public MenuItem execute(MenuItem menuItem) {
        var menu = menuGateway.findById(menuItem.getMenu().getId());
        return menuItemGateway.save(menuItem);
    }
}
