package br.com.foodwise.platform.application.service;

import br.com.foodwise.platform.application.usecase.menuItem.CreateMenuItemUseCase;
import br.com.foodwise.platform.application.usecase.menuItem.RetrieveAllMenusItemByItemNameUseCase;
import br.com.foodwise.platform.application.usecase.menuItem.RetrieveAllMenusItemUseCase;
import br.com.foodwise.platform.application.usecase.menuItem.RetrieveMenuItemUseCase;
import br.com.foodwise.platform.application.usecase.menuItem.UpdateMenuItemUseCase;
import br.com.foodwise.platform.domain.entities.MenuItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final CreateMenuItemUseCase createMenuItemUseCase;
    private final RetrieveMenuItemUseCase retrieveMenuItemUseCase;
    private final RetrieveAllMenusItemUseCase retrieveAllMenusItemsUseCase;
    private final RetrieveAllMenusItemByItemNameUseCase retrieveAllMenusItemByItemNameUseCase;
    private final UpdateMenuItemUseCase updateMenuItemUseCase;

    public MenuItem createMenuItem(MenuItem menuItem) {
        return createMenuItemUseCase.execute(menuItem);
    }

    public MenuItem getMenuItemById(Long id) {
        return retrieveMenuItemUseCase.execute(id);
    }

    public List<MenuItem> getAllMenusItemByItemName(String itemName) {
        return retrieveAllMenusItemByItemNameUseCase.execute(itemName);
    }

    public List<MenuItem> getAllMenuItems() {
        return retrieveAllMenusItemsUseCase.execute();
    }

    public MenuItem updateMenuItem(MenuItem menu) {
        return updateMenuItemUseCase.execute(menu);
    }
}
