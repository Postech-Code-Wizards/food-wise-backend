package br.com.foodwise.platform.application.service;

import br.com.foodwise.platform.application.usecase.menu.CreateMenuUseCase;
import br.com.foodwise.platform.application.usecase.menu.DeleteMenuUseCase;
import br.com.foodwise.platform.application.usecase.menu.RetrieveAllMenusByRestaurantNameUseCase;
import br.com.foodwise.platform.application.usecase.menu.RetrieveAllMenusUseCase;
import br.com.foodwise.platform.application.usecase.menu.RetrieveMenuUseCase;
import br.com.foodwise.platform.application.usecase.menu.UpdateMenuUseCase;
import br.com.foodwise.platform.domain.entities.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static br.com.foodwise.platform.factory.EntityFactory.buildMenu;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuServiceTest {
    @InjectMocks
    private MenuService menuService;

    @Mock
    private CreateMenuUseCase createMenuUseCase;
    @Mock
    private RetrieveMenuUseCase retrieveMenuUseCase;
    @Mock
    private RetrieveAllMenusUseCase retrieveAllMenusUseCase;
    @Mock
    private RetrieveAllMenusByRestaurantNameUseCase retrieveAllMenusByRestaurantNameUseCase;
    @Mock
    private UpdateMenuUseCase updateMenuUseCase;
    @Mock
    private DeleteMenuUseCase deleteMenuUseCase;

    private Menu menu;

    @BeforeEach
    void setUp() {
        menu = buildMenu();
    }

    @Test
    void shouldCreateMenuSuccessfully() {
        when(createMenuUseCase.execute(menu)).thenReturn(menu);

        Menu createdMenu = menuService.createMenu(menu);

        assertEquals(menu, createdMenu);
        verify(createMenuUseCase, times(1)).execute(menu);
    }

    @Test
    void shouldRetrieveMenuByIdSuccessfully() {
        Long menuId = 1L;
        when(retrieveMenuUseCase.execute(menuId)).thenReturn(menu);

        Menu retrievedMenu = menuService.getMenuById(menuId);

        assertEquals(menu, retrievedMenu);
        verify(retrieveMenuUseCase, times(1)).execute(menuId);
    }

    @Test
    void shouldRetrieveAllMenusByRestaurantNameSuccessfully() {
        String restaurantName = "Test Restaurant";
        when(retrieveAllMenusByRestaurantNameUseCase.execute(restaurantName)).thenReturn(List.of(menu));

        List<Menu> menus = menuService.getAllMenusByRestaurantName(restaurantName);

        assertEquals(1, menus.size());
        assertEquals(menu, menus.get(0));
        verify(retrieveAllMenusByRestaurantNameUseCase, times(1)).execute(restaurantName);
    }

    @Test
    void shouldRetrieveAllMenusSuccessfully() {
        when(retrieveAllMenusUseCase.execute()).thenReturn(List.of(menu));

        List<Menu> menus = menuService.getAllMenus();

        assertEquals(1, menus.size());
        assertEquals(menu, menus.get(0));
        verify(retrieveAllMenusUseCase, times(1)).execute();
    }

    @Test
    void shouldUpdateMenuSuccessfully() {
        when(updateMenuUseCase.execute(menu)).thenReturn(menu);

        Menu updatedMenu = menuService.updateMenu(menu);

        assertEquals(menu, updatedMenu);
        verify(updateMenuUseCase, times(1)).execute(menu);
    }

    @Test
    void shouldDeleteMenuSuccessfully() {
        Long menuId = 1L;

        menuService.deleteMenu(menuId);

        verify(deleteMenuUseCase, times(1)).execute(menuId);
    }
}