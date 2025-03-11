package br.com.foodwise.platform.infrastructure.rest.controller;

import br.com.foodwise.platform.application.facade.MenuItemFacade;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class MenuItemControllerTest {

    @Mock
    private MenuItemFacade menuItemFacade;

    @InjectMocks
    private MenuItemController menuItemController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create a MenuItem and return CREATED response")
    void should_create_a_MenuItem_and_return_CREATED_response() {

        RegisterMenuItemRequest request = Instancio.create(RegisterMenuItemRequest.class);
        MenuItemResponse response = Instancio.create(MenuItemResponse.class);
        when(menuItemFacade.createMenuItem(request)).thenReturn(response);

        ResponseEntity<MenuItemResponse> result = menuItemController.createMenuItem(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    @DisplayName("Should get a MenuItem by id and return OK response")
    void should_get_a_MenuItem_by_id_and_return_OK_response() {

        Long id = Instancio.create(Long.class);
        MenuItemResponse response = Instancio.create(MenuItemResponse.class);
        when(menuItemFacade.getMenuItemById(id)).thenReturn(response);

        ResponseEntity<MenuItemResponse> result = menuItemController.getMenuItemById(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    @DisplayName("Should get MenuItems by item name and return OK response")
    void should_get_MenuItems_by_item_name_and_return_OK_response() {

        String name = Instancio.create(String.class);
        List<MenuItemResponse> response = Instancio.ofList(MenuItemResponse.class).create();
        when(menuItemFacade.getAllMenusItemByItemName(name)).thenReturn(response);

        ResponseEntity<List<MenuItemResponse>> result = menuItemController.getMenusItemsByItemName(name);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    @DisplayName("Should get all MenuItems and return OK response")
    void should_get_all_MenuItems_and_return_OK_response() {

        List<MenuItemResponse> response = Instancio.ofList(MenuItemResponse.class).create();
        when(menuItemFacade.getAllMenuItems()).thenReturn(response);

        ResponseEntity<List<MenuItemResponse>> result = menuItemController.getAllMenuItems();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    @DisplayName("Should update a MenuItem and return OK response")
    void should_update_a_MenuItem_and_return_OK_response() {

        Long id = Instancio.create(Long.class);
        RegisterMenuItemRequest request = Instancio.create(RegisterMenuItemRequest.class);
        MenuItemResponse response = Instancio.create(MenuItemResponse.class);
        when(menuItemFacade.updateMenuItem(id, request)).thenReturn(response);

        ResponseEntity<MenuItemResponse> result = menuItemController.updateMenuItem(id, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    @DisplayName("Should update available MenuItem and return OK response")
    void should_update_available_MenuItem_and_return_OK_response() {

        Long id = Instancio.create(Long.class);
        RegisterMenuItemAvailable available = Instancio.create(RegisterMenuItemAvailable.class);
        MenuItemResponse response = Instancio.create(MenuItemResponse.class);
        when(menuItemFacade.updateAvailableMenuItem(id, available)).thenReturn(response);

        ResponseEntity<MenuItemResponse> result = menuItemController.updateAvailableMenuItem(id, available);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

}