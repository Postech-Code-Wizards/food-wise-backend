package br.com.foodwise.platform.application.service;

import br.com.foodwise.platform.application.usecase.menuItem.CreateMenuItemUseCase;
import br.com.foodwise.platform.application.usecase.menuItem.RetrieveAllMenusItemByItemNameUseCase;
import br.com.foodwise.platform.application.usecase.menuItem.RetrieveAllMenusItemUseCase;
import br.com.foodwise.platform.application.usecase.menuItem.RetrieveMenuItemUseCase;
import br.com.foodwise.platform.application.usecase.menuItem.UpdateMenuItemUseCase;
import br.com.foodwise.platform.domain.entities.MenuItem;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menuItem.RegisterMenuItemRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static br.com.foodwise.platform.factory.EntityFactory.buildMenuItem;
import static br.com.foodwise.platform.factory.EntityFactory.buildRegisterMenuItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuItemServiceTest {

    @InjectMocks
    private MenuItemService menuItemService;

    @Mock
    private CreateMenuItemUseCase createMenuItemUseCase;
    @Mock
    private RetrieveMenuItemUseCase retrieveMenuItemUseCase;
    @Mock
    private RetrieveAllMenusItemUseCase retrieveAllMenusItemUseCase;
    @Mock
    private RetrieveAllMenusItemByItemNameUseCase retrieveAllMenusItemByItemNameUseCase;
    @Mock
    private UpdateMenuItemUseCase updateMenuItemUseCase;

    private MenuItem menuItem;

    private RegisterMenuItemRequest registerMenuItemRequest;

    @BeforeEach
    void setUp() {
        menuItem = buildMenuItem();
    }

    @BeforeEach
    void setUpRegisterMenuItemRequest() {
        registerMenuItemRequest = buildRegisterMenuItem();
    }

    @Test
    void shouldCreateMenuItemSuccessfully() {
        when(createMenuItemUseCase.execute(menuItem)).thenReturn(menuItem);

        MenuItem createdMenuItem = menuItemService.createMenuItem(menuItem);

        assertEquals(menuItem, createdMenuItem);
        verify(createMenuItemUseCase, times(1)).execute(menuItem);
    }

    @Test
    void shouldRetrieveMenuItemByIdSuccessfully() {
        Long menuItemId = 1L;
        when(retrieveMenuItemUseCase.execute(menuItemId)).thenReturn(menuItem);

        MenuItem retrievedMenuItem = menuItemService.getMenuItemById(menuItemId);

        assertEquals(menuItem, retrievedMenuItem);
        verify(retrieveMenuItemUseCase, times(1)).execute(menuItemId);
    }

    @Test
    void shouldRetrieveAllMenusItemByRestaurantNameSuccessfully() {
        String menuItemName = "Test Menu Item";
        when(retrieveAllMenusItemByItemNameUseCase.execute(menuItemName)).thenReturn(List.of(menuItem));

        List<MenuItem> menusItem = menuItemService.getAllMenusItemByItemName(menuItemName);

        assertEquals(1, menusItem.size());
        assertEquals(menuItem, menusItem.get(0));
        verify(retrieveAllMenusItemByItemNameUseCase, times(1)).execute(menuItemName);
    }

    @Test
    void shouldRetrieveAllMenusItemSuccessfully() {
        when(retrieveAllMenusItemUseCase.execute()).thenReturn(List.of(menuItem));

        List<MenuItem> menusItem = menuItemService.getAllMenuItems();

        assertEquals(1, menusItem.size());
        assertEquals(menuItem, menusItem.get(0));
        verify(retrieveAllMenusItemUseCase, times(1)).execute();
    }

}
