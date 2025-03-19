package br.com.foodwise.platform.application.facade;

import br.com.foodwise.platform.application.facade.converter.menuItem.MenuItemToMenuItemResponseConverter;
import br.com.foodwise.platform.application.facade.converter.menuItem.MenuItemUpdateRequestToMenuItemConverter;
import br.com.foodwise.platform.application.facade.converter.menuItem.RegisterMenuItemRequestToMenuItemConverter;
import br.com.foodwise.platform.application.usecase.menu.RetrieveMenuUseCase;
import br.com.foodwise.platform.application.usecase.menu.item.CreateMenuItemUseCase;
import br.com.foodwise.platform.application.usecase.menu.item.RetrieveAllMenusItemByItemNameUseCase;
import br.com.foodwise.platform.application.usecase.menu.item.RetrieveAllMenusItemUseCase;
import br.com.foodwise.platform.application.usecase.menu.item.RetrieveMenuItemUseCase;
import br.com.foodwise.platform.application.usecase.menu.item.UpdateMenuItemUseCase;
import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menuItem.RegisterMenuItemAvailable;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menuItem.RegisterMenuItemRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.MenuItemResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MenuItemFacadeTest {

    @Mock
    private MenuFacade menuFacade;

    @Mock
    private MenuItemUpdateRequestToMenuItemConverter menuItemUpdateRequestToMenuItemConverter;

    @Mock
    private CreateMenuItemUseCase createMenuItemUseCase;

    @Mock
    private RetrieveMenuItemUseCase retrieveMenuItemUseCase;

    @Mock
    private RetrieveAllMenusItemUseCase retrieveAllMenusItemsUseCase;

    @Mock
    private RetrieveAllMenusItemByItemNameUseCase retrieveAllMenusItemByItemNameUseCase;

    @Mock
    private UpdateMenuItemUseCase updateMenuItemUseCase;

    @Mock
    private RetrieveMenuUseCase retrieveMenuUseCase;

    @Mock
    private RegisterMenuItemRequestToMenuItemConverter registerMenuItemRequestToMenuItemConverter;

    @Mock
    private MenuItemToMenuItemResponseConverter menuItemToMenuItemResponseConverter;

    @InjectMocks
    private MenuItemFacade menuItemFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test createMenuItem")
    void testCreateMenuItem() {

        RegisterMenuItemRequest request = Instancio.create(RegisterMenuItemRequest.class);
        Menu menu = Instancio.create(Menu.class);
        MenuItem menuItem = Instancio.create(MenuItem.class);
        MenuItem menuItemCreated = Instancio.create(MenuItem.class);
        MenuItemResponse response = Instancio.create(MenuItemResponse.class);

        when(retrieveMenuUseCase.execute(request.getMenu().getId())).thenReturn(menu);
        when(registerMenuItemRequestToMenuItemConverter.convert(request, menu)).thenReturn(menuItem);
        when(createMenuItemUseCase.execute(menuItem)).thenReturn(menuItemCreated);
        when(menuItemToMenuItemResponseConverter.convert(menuItemCreated)).thenReturn(response);

        MenuItemResponse result = menuItemFacade.createMenuItem(request);

        assertEquals(response, result);
        verify(retrieveMenuUseCase).execute(request.getMenu().getId());
        verify(registerMenuItemRequestToMenuItemConverter).convert(request, menu);
        verify(createMenuItemUseCase).execute(menuItem);
        verify(menuItemToMenuItemResponseConverter).convert(menuItemCreated);
    }

    @Test
    @DisplayName("Test getMenuItemById")
    void testGetMenuItemById() {

        Long id = Instancio.create(Long.class);
        MenuItem menuItem = Instancio.create(MenuItem.class);
        MenuItemResponse response = Instancio.create(MenuItemResponse.class);

        when(retrieveMenuItemUseCase.execute(id)).thenReturn(menuItem);
        when(menuItemToMenuItemResponseConverter.convert(menuItem)).thenReturn(response);

        MenuItemResponse result = menuItemFacade.getMenuItemById(id);

        assertEquals(response, result);
        verify(retrieveMenuItemUseCase).execute(id);
        verify(menuItemToMenuItemResponseConverter).convert(menuItem);
    }

    @Test
    @DisplayName("Test getAllMenusItemByItemName")
    void testGetAllMenusItemByItemName() {

        String itemName = Instancio.create(String.class);
        List<MenuItem> menuItems = Instancio.stream(MenuItem.class).limit(3).collect(Collectors.toList());
        List<MenuItemResponse> responses = Instancio.stream(MenuItemResponse.class).limit(3).collect(Collectors.toList());

        when(retrieveAllMenusItemByItemNameUseCase.execute(itemName)).thenReturn(menuItems);
        for (int i = 0; i < menuItems.size(); i++) {
            when(menuItemToMenuItemResponseConverter.convert(menuItems.get(i))).thenReturn(responses.get(i));
        }

        List<MenuItemResponse> result = menuItemFacade.getAllMenusItemByItemName(itemName);

        assertEquals(responses, result);
        verify(retrieveAllMenusItemByItemNameUseCase).execute(itemName);
        menuItems.forEach(item -> verify(menuItemToMenuItemResponseConverter).convert(item));
    }

    @Test
    @DisplayName("Test getAllMenuItems")
    void testGetAllMenuItems() {

        List<MenuItem> menuItems = Instancio.stream(MenuItem.class).limit(3).collect(Collectors.toList());
        List<MenuItemResponse> responses = Instancio.stream(MenuItemResponse.class).limit(3).collect(Collectors.toList());

        when(retrieveAllMenusItemsUseCase.execute()).thenReturn(menuItems);
        for (int i = 0; i < menuItems.size(); i++) {
            when(menuItemToMenuItemResponseConverter.convert(menuItems.get(i))).thenReturn(responses.get(i));
        }

        List<MenuItemResponse> result = menuItemFacade.getAllMenuItems();

        assertEquals(responses, result);
        verify(retrieveAllMenusItemsUseCase).execute();
        menuItems.forEach(item -> verify(menuItemToMenuItemResponseConverter).convert(item));
    }

    @Test
    @DisplayName("Test updateMenuItem")
    void testUpdateMenuItem() {

        Long id = Instancio.create(Long.class);
        RegisterMenuItemRequest request = Instancio.create(RegisterMenuItemRequest.class);
        MenuItem existingMenuItem = Instancio.create(MenuItem.class);
        Menu menu = Instancio.create(Menu.class);
        MenuItem updatedMenuItem = Instancio.create(MenuItem.class);
        MenuItemResponse response = Instancio.create(MenuItemResponse.class);

        when(retrieveMenuItemUseCase.execute(id)).thenReturn(existingMenuItem);
        when(retrieveMenuUseCase.execute(request.getMenu().getId())).thenReturn(menu);
        when(menuItemUpdateRequestToMenuItemConverter.convert(existingMenuItem, request)).thenReturn(updatedMenuItem);
        when(updateMenuItemUseCase.execute(updatedMenuItem)).thenReturn(updatedMenuItem);
        when(menuItemToMenuItemResponseConverter.convert(updatedMenuItem)).thenReturn(response);

        MenuItemResponse result = menuItemFacade.updateMenuItem(id, request);

        assertEquals(response, result);
        verify(retrieveMenuItemUseCase).execute(id);
        verify(retrieveMenuUseCase).execute(anyLong());
        verify(menuItemUpdateRequestToMenuItemConverter).convert(existingMenuItem, request);
        verify(updateMenuItemUseCase).execute(any(MenuItem.class));
        verify(menuItemToMenuItemResponseConverter).convert(updatedMenuItem);
    }

    @Test
    @DisplayName("Test updateAvailableMenuItem")
    void testUpdateAvailableMenuItem() {

        Long id = Instancio.create(Long.class);
        RegisterMenuItemAvailable available = Instancio.create(RegisterMenuItemAvailable.class);
        MenuItem existingMenuItem = Instancio.create(MenuItem.class);
        MenuItem updatedMenuItem = Instancio.create(MenuItem.class);
        MenuItemResponse response = Instancio.create(MenuItemResponse.class);

        when(retrieveMenuItemUseCase.execute(id)).thenReturn(existingMenuItem);
        when(updateMenuItemUseCase.execute(any(MenuItem.class))).thenReturn(updatedMenuItem);
        when(menuItemToMenuItemResponseConverter.convert(updatedMenuItem)).thenReturn(response);

        MenuItemResponse result = menuItemFacade.updateAvailableMenuItem(id, available);

        assertEquals(response, result);
        verify(retrieveMenuItemUseCase).execute(id);
        verify(updateMenuItemUseCase).execute(any(MenuItem.class));
        verify(menuItemToMenuItemResponseConverter).convert(updatedMenuItem);
    }

}