package br.com.foodwise.platform.application.service;

import br.com.foodwise.platform.application.usecase.menuItem.*;
import br.com.foodwise.platform.domain.entities.MenuItem;
import br.com.foodwise.platform.infrastructure.rest.converter.menuItem.MenuItemUpdateRequestToMenuItemConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menuItem.RegisterMenuItemAvailable;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menuItem.RegisterMenuItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuService menuService;
    private final MenuItemUpdateRequestToMenuItemConverter menuItemUpdateRequestToMenuItemConverter;

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

    public MenuItem updateMenuItem(Long id, RegisterMenuItemRequest menuItemRequestDTO) {
        MenuItem existingMenuItem = getMenuItemById(id);
        var menu = menuService.getMenuById(menuItemRequestDTO.getMenu().getId());
        menuItemRequestDTO.setMenu(menu);
        var menuItemConverter = menuItemUpdateRequestToMenuItemConverter.convert(existingMenuItem, menuItemRequestDTO);
        return updateMenuItemUseCase.execute(menuItemConverter);
    }

    public MenuItem updateAvailableMenuItem(Long id, RegisterMenuItemAvailable available) {
        MenuItem existingMenuItem = getMenuItemById(id);
        existingMenuItem.setAvailable(available.getAvailable());
        return updateMenuItemUseCase.execute(existingMenuItem);
    }
}
