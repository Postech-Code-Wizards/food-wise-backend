package br.com.foodwise.platform.infrastructure.rest.controller;

import br.com.foodwise.platform.application.facade.MenuItemFacade;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menuItem.RegisterMenuItemAvailable;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menuItem.RegisterMenuItemRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.MenuItemResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/menu-item")
@RequiredArgsConstructor
public class MenuItemController implements MenuItemApi {

    private final MenuItemFacade menuItemFacade;

    @Override
    public ResponseEntity<MenuItemResponse> createMenuItem(@RequestBody @Valid RegisterMenuItemRequest menuItemRequestDTO) {
        var menuItemResponse = menuItemFacade.createMenuItem(menuItemRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItemResponse);
    }

    @Override
    public ResponseEntity<MenuItemResponse> getMenuItemById(@PathVariable Long id) {
        var menuItemResponse = menuItemFacade.getMenuItemById(id);
        return ResponseEntity.ok(menuItemResponse);
    }

    @Override
    public ResponseEntity<List<MenuItemResponse>> getMenusItemsByItemName(@PathVariable String name) {
        return ResponseEntity.ok(menuItemFacade
                .getAllMenusItemByItemName(name));
    }

    @Override
    public ResponseEntity<List<MenuItemResponse>> getAllMenuItems() {
        return ResponseEntity.ok(menuItemFacade.getAllMenuItems());
    }

    @Override
    public ResponseEntity<MenuItemResponse> updateMenuItem(@PathVariable Long id, @RequestBody @Valid RegisterMenuItemRequest menuItemRequestDTO) {
        var updatedMenuItem = menuItemFacade.updateMenuItem(id, menuItemRequestDTO);
        return ResponseEntity.ok(updatedMenuItem);
    }

    @Override
    public ResponseEntity<MenuItemResponse> updateAvailableMenuItem(@PathVariable Long id, @RequestBody @Valid RegisterMenuItemAvailable available) {
        var updatedMenuItem = menuItemFacade.updateAvailableMenuItem(id, available);
        return ResponseEntity.ok(updatedMenuItem);
    }

}
