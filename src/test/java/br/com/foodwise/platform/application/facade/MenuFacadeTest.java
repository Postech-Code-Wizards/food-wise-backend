package br.com.foodwise.platform.application.facade;

import br.com.foodwise.platform.application.usecase.menu.*;
import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.application.facade.converter.menu.MenuToMenuResponseConverter;
import br.com.foodwise.platform.application.facade.converter.menu.MenuUpdateRequestToMenuConverter;
import br.com.foodwise.platform.application.facade.converter.menu.RegisterMenuRequestToMenuConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menu.RegisterMenuRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.MenuResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuFacadeTest {
    @InjectMocks
    private MenuFacade menuFacade;

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

    @Mock
    private RegisterMenuRequestToMenuConverter registerMenuRequestToMenuConverter;

    @Mock
    private MenuToMenuResponseConverter menuToMenuResponseConverter;

    @Mock
    private MenuUpdateRequestToMenuConverter menuUpdateRequestToMenuConverter;

    private Menu menu;
    private RegisterMenuRequest registerMenuRequest;
    private MenuResponse menuResponse;

    @BeforeEach
    void setUp() {
        menu = Instancio.create(Menu.class);
        registerMenuRequest = Instancio.create(RegisterMenuRequest.class);
        menuResponse = Instancio.create(MenuResponse.class);
    }

    @Test
    void shouldCreateMenuSuccessfully() {
        when(createMenuUseCase.execute(menu)).thenReturn(menu);
        when(registerMenuRequestToMenuConverter.convert(registerMenuRequest)).thenReturn(menu);
        when(menuToMenuResponseConverter.convert(menu)).thenReturn(menuResponse);

        MenuResponse createdMenu = menuFacade.createMenu(registerMenuRequest);

        assertEquals(menuResponse, createdMenu);
        verify(createMenuUseCase, times(1)).execute(menu);
    }

    @Test
    void shouldRetrieveMenuByIdSuccessfully() {
        Long menuId = 1L;
        when(retrieveMenuUseCase.execute(menuId)).thenReturn(menu);
        when(menuToMenuResponseConverter.convert(menu)).thenReturn(menuResponse);

        MenuResponse retrievedMenu = menuFacade.getMenuById(menuId);

        assertEquals(menuResponse, retrievedMenu);
        verify(retrieveMenuUseCase, times(1)).execute(menuId);
    }

    @Test
    void shouldRetrieveAllMenusByRestaurantNameSuccessfully() {
        String restaurantName = "Test Restaurant";
        when(retrieveAllMenusByRestaurantNameUseCase.execute(restaurantName)).thenReturn(List.of(menu));
        when(menuToMenuResponseConverter.convert(menu)).thenReturn(menuResponse);

        List<MenuResponse> menus = menuFacade.getAllMenusByRestaurantName(restaurantName);

        assertEquals(1, menus.size());
        assertEquals(menuResponse, menus.getFirst());
        verify(retrieveAllMenusByRestaurantNameUseCase, times(1)).execute(restaurantName);
    }

    @Test
    void shouldRetrieveAllMenusSuccessfully() {
        when(retrieveAllMenusUseCase.execute()).thenReturn(List.of(menu));
        when(menuToMenuResponseConverter.convert(menu)).thenReturn(menuResponse);

        List<MenuResponse> menus = menuFacade.getAllMenus();

        assertEquals(1, menus.size());
        assertEquals(menuResponse, menus.getFirst());
        verify(retrieveAllMenusUseCase, times(1)).execute();
    }

    @Test
    void shouldUpdateMenuSuccessfully() {

        Long menuId = 1L;

        when(updateMenuUseCase.execute(menu)).thenReturn(menu);
        when(retrieveMenuUseCase.execute(menuId)).thenReturn(menu);
        when(menuUpdateRequestToMenuConverter.convert(any(RegisterMenuRequest.class), any(Menu.class))).thenReturn(menu);
        when(menuToMenuResponseConverter.convert(menu)).thenReturn(menuResponse);

        MenuResponse updatedMenu = menuFacade.updateMenu(menuId, registerMenuRequest);

        assertEquals(menuResponse, updatedMenu);
        verify(updateMenuUseCase, times(1)).execute(menu);
    }

    @Test
    void shouldDeleteMenuSuccessfully() {
        Long menuId = 1L;

        menuFacade.deleteMenu(menuId);

        verify(deleteMenuUseCase, times(1)).execute(menuId);
    }
}