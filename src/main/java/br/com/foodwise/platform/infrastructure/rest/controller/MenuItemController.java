package br.com.foodwise.platform.infrastructure.rest.controller;

import br.com.foodwise.platform.application.service.MenuItemService;
import br.com.foodwise.platform.domain.entities.Menu;
import br.com.foodwise.platform.domain.entities.MenuItem;
import br.com.foodwise.platform.infrastructure.rest.converter.menuItem.MenuItemToMenuItemResponseConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.menuItem.MenuItemUpdateRequestToMenuItemConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.menuItem.RegisterMenuItemRequestToMenuItemConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menu.RegisterMenuRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menuItem.RegisterMenuItemRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.MenuItemResponse;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.MenuResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/menu-item")
@RequiredArgsConstructor
public class MenuItemController {
    private final MenuItemService menuItemService;

    private final RegisterMenuItemRequestToMenuItemConverter registerMenuItemRequestToMenuItemConverter;
    private final MenuItemToMenuItemResponseConverter menuItemToMenuItemResponseConverter;
    private final MenuItemUpdateRequestToMenuItemConverter menuItemUpdateRequestToMenuItemConverter;

    @PostMapping
    public ResponseEntity<MenuItemResponse> createMenuItem(@RequestBody @Valid RegisterMenuItemRequest menuItemRequestDTO) {
        var createdMenuitem = menuItemService.createMenuItem(convertToMenuItem(menuItemRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToMenuItemResponse(createdMenuitem));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemResponse> getMenuItemById(@PathVariable Long id) {
        var menuItem = fetchMenuItemById(id);
        return ResponseEntity.ok(convertToMenuItemResponse(menuItem));
    }

    @GetMapping("/item/{name}")
    public ResponseEntity<List<MenuItemResponse>> getMenusItemsByItemName(@PathVariable String name) {
        return ResponseEntity.ok(menuItemService
                .getAllMenusItemByItemName(name).stream()
                .map(this::convertToMenuItemResponse)
                .toList());
    }

    @GetMapping
    public ResponseEntity<List<MenuItemResponse>> getAllMenuItems() {
        return ResponseEntity.ok(menuItemService.getAllMenuItems().stream()
                .map(this::convertToMenuItemResponse)
                .toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItemResponse> updateMenuItem(@PathVariable Long id, @RequestBody @Valid RegisterMenuItemRequest menuItemRequestDTO) {
        var updatedMenuItem = processUpdateMenuItem(id, menuItemRequestDTO);
        return ResponseEntity.ok(convertToMenuItemResponse(updatedMenuItem));
    }

    private MenuItem fetchMenuItemById(Long id) {
        return menuItemService.getMenuItemById(id);
    }

    private MenuItem processUpdateMenuItem(Long id, RegisterMenuItemRequest menuItemRequestDTO) {
        MenuItem existingMenuItem = fetchMenuItemById(id);
        menuItemUpdateRequestToMenuItemConverter.convert(menuItemRequestDTO, existingMenuItem);
        return menuItemService.updateMenuItem(existingMenuItem);
    }

    private MenuItem convertToMenuItem(RegisterMenuItemRequest menuItemRequestDTO) {
        return registerMenuItemRequestToMenuItemConverter.convert(menuItemRequestDTO);
    }

    private MenuItemResponse convertToMenuItemResponse(MenuItem menuItem) {
        return menuItemToMenuItemResponseConverter.convert(menuItem);
    }
}
