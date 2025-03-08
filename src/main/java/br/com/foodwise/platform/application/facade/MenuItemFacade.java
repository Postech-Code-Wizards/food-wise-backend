package br.com.foodwise.platform.application.facade;

import br.com.foodwise.platform.application.usecase.menuItem.*;
import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.application.facade.converter.menuItem.MenuItemToMenuItemResponseConverter;
import br.com.foodwise.platform.application.facade.converter.menuItem.MenuItemUpdateRequestToMenuItemConverter;
import br.com.foodwise.platform.application.facade.converter.menuItem.RegisterMenuItemRequestToMenuItemConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menuItem.RegisterMenuItemAvailable;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menuItem.RegisterMenuItemRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.MenuItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemFacade {

    private final MenuFacade menuFacade;
    private final MenuItemUpdateRequestToMenuItemConverter menuItemUpdateRequestToMenuItemConverter;

    private final CreateMenuItemUseCase createMenuItemUseCase;
    private final RetrieveMenuItemUseCase retrieveMenuItemUseCase;
    private final RetrieveAllMenusItemUseCase retrieveAllMenusItemsUseCase;
    private final RetrieveAllMenusItemByItemNameUseCase retrieveAllMenusItemByItemNameUseCase;
    private final UpdateMenuItemUseCase updateMenuItemUseCase;

    private final RegisterMenuItemRequestToMenuItemConverter registerMenuItemRequestToMenuItemConverter;
    private final MenuItemToMenuItemResponseConverter menuItemToMenuItemResponseConverter;

    public MenuItemResponse createMenuItem(RegisterMenuItemRequest registerMenuItemRequest) {
        var menuItem = registerMenuItemRequestToMenuItemConverter.convert(registerMenuItemRequest);
        var menuItemCreated = createMenuItemUseCase.execute(menuItem);
        return menuItemToMenuItemResponseConverter.convert(menuItemCreated);
    }

    public MenuItemResponse getMenuItemById(Long id) {
        var menuItem = retrieveMenuItemUseCase.execute(id);
        return menuItemToMenuItemResponseConverter.convert(menuItem);
    }

    public List<MenuItemResponse> getAllMenusItemByItemName(String itemName) {
        var menuItems = retrieveAllMenusItemByItemNameUseCase.execute(itemName);
        return menuItems.stream().map(menuItemToMenuItemResponseConverter::convert).toList();
    }

    public List<MenuItemResponse> getAllMenuItems() {
        var menuItems = retrieveAllMenusItemsUseCase.execute();
        return menuItems.stream().map(menuItemToMenuItemResponseConverter::convert).toList();
    }

    public MenuItemResponse updateMenuItem(Long id, RegisterMenuItemRequest menuItemRequestDTO) {
        MenuItem existingMenuItem = retrieveMenuItemUseCase.execute(id);
        var menu = menuFacade.getMenuById(menuItemRequestDTO.getMenu().getId());
//        menuItemRequestDTO.setMenu(menu);
//        var menuItemConverter = menuItemUpdateRequestToMenuItemConverter.convert(existingMenuItem, menuItemRequestDTO);
//        return updateMenuItemUseCase.execute(menuItemConverter);
        return null;
    }

    public MenuItemResponse updateAvailableMenuItem(Long id, RegisterMenuItemAvailable available) {
//        MenuItem existingMenuItem = getMenuItemById(id);
//        existingMenuItem.setAvailable(available.getAvailable());
//        return updateMenuItemUseCase.execute(existingMenuItem);
        return null;
    }
}
