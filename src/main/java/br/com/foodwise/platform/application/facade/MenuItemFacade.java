package br.com.foodwise.platform.application.facade;

import br.com.foodwise.platform.application.facade.converter.menuItem.MenuItemToMenuItemResponseConverter;
import br.com.foodwise.platform.application.facade.converter.menuItem.MenuItemUpdateRequestToMenuItemConverter;
import br.com.foodwise.platform.application.facade.converter.menuItem.RegisterMenuItemRequestToMenuItemConverter;
import br.com.foodwise.platform.application.usecase.menu.RetrieveMenuUseCase;
import br.com.foodwise.platform.application.usecase.menuItem.CreateMenuItemUseCase;
import br.com.foodwise.platform.application.usecase.menuItem.RetrieveAllMenusItemByItemNameUseCase;
import br.com.foodwise.platform.application.usecase.menuItem.RetrieveAllMenusItemUseCase;
import br.com.foodwise.platform.application.usecase.menuItem.RetrieveMenuItemUseCase;
import br.com.foodwise.platform.application.usecase.menuItem.UpdateMenuItemUseCase;
import br.com.foodwise.platform.domain.MenuItem;
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
    private final RetrieveMenuUseCase retrieveMenuUseCase;

    private final RegisterMenuItemRequestToMenuItemConverter registerMenuItemRequestToMenuItemConverter;
    private final MenuItemToMenuItemResponseConverter menuItemToMenuItemResponseConverter;

    public MenuItemResponse createMenuItem(RegisterMenuItemRequest registerMenuItemRequest) {
        var menu = retrieveMenuUseCase.execute(registerMenuItemRequest.getMenu().getId());
        var menuItem = registerMenuItemRequestToMenuItemConverter.convert(registerMenuItemRequest, menu);
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
        var existingMenuItem = retrieveMenuItemUseCase.execute(id);
        var menu = retrieveMenuUseCase.execute(menuItemRequestDTO.getMenu().getId());
        menuItemRequestDTO.setMenu(menu);
        var menuItemConverter = menuItemUpdateRequestToMenuItemConverter.convert(existingMenuItem, menuItemRequestDTO);

        var menuItemSaved = updateMenuItemUseCase.execute(menuItemConverter);
        return menuItemToMenuItemResponseConverter.convert(menuItemSaved);
    }

    public MenuItemResponse updateAvailableMenuItem(Long id, RegisterMenuItemAvailable available) {
        var existingMenuItem = retrieveMenuItemUseCase.execute(id);
        var menuItem = populateAvailableMenuItem(available, existingMenuItem);
        var menuItemSaved = updateMenuItemUseCase.execute(menuItem);

        return menuItemToMenuItemResponseConverter.convert(menuItemSaved);
    }

    private static MenuItem populateAvailableMenuItem(RegisterMenuItemAvailable available, MenuItem existingMenuItem) {
        return MenuItem.builder()
                .id(existingMenuItem.getId())
                .name(existingMenuItem.getName())
                .description(existingMenuItem.getDescription())
                .price(existingMenuItem.getPrice())
                .category(existingMenuItem.getCategory())
                .isAvailable(available.getAvailable())
                .imageUrl(existingMenuItem.getImageUrl())
                .createdAt(existingMenuItem.getCreatedAt())
                .updatedAt(existingMenuItem.getUpdatedAt())
                .menu(existingMenuItem.getMenu())
                .build();
    }

}
