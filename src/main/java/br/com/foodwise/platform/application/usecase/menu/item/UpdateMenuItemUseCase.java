package br.com.foodwise.platform.application.usecase.menu.item;

import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.gateway.MenuItemGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateMenuItemUseCase {

    private final MenuItemGateway menuItemGateway;

    public MenuItem execute(MenuItem menuItem) {
        return menuItemGateway.save(menuItem);
    }

}
