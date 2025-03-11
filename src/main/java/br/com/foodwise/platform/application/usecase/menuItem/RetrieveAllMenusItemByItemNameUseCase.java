package br.com.foodwise.platform.application.usecase.menuItem;

import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.gateway.MenuItemGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RetrieveAllMenusItemByItemNameUseCase {

    private final MenuItemGateway menuItemGateway;

    public List<MenuItem> execute(String itemName) {
        return menuItemGateway.findMenuItemByName(itemName);
    }
}
