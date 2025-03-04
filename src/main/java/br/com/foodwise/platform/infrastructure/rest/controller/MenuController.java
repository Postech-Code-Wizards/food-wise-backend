package br.com.foodwise.platform.infrastructure.rest.controller;

import br.com.foodwise.platform.application.service.MenuService;
import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.infrastructure.rest.converter.menu.MenuToMenuResponseConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.menu.MenuUpdateRequestToMenuConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.menu.RegisterMenuRequestToMenuConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menu.RegisterMenuRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.MenuResponse;
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
@RequestMapping("api/v1/menu")
@RequiredArgsConstructor
public class MenuController implements MenuApi {
    private final MenuService menuService;

    private final RegisterMenuRequestToMenuConverter registerMenuRequestToMenuConverter;
    private final MenuToMenuResponseConverter menuToMenuResponseConverter;
    private final MenuUpdateRequestToMenuConverter menuUpdateRequestToMenuConverter;

    @Override
    public ResponseEntity<MenuResponse> createMenu(@RequestBody @Valid RegisterMenuRequest menuRequestDTO) {
        var createdMenu = menuService.createMenu(convertToMenu(menuRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToMenuResponse(createdMenu));
    }

    @Override
    public ResponseEntity<MenuResponse> getMenuById(@PathVariable Long id) {
        var menu = fetchMenuById(id);
        return ResponseEntity.ok(convertToMenuResponse(menu));
    }

    @Override
    public ResponseEntity<List<MenuResponse>> getMenusByRestaurantName(@PathVariable String name) {
        return ResponseEntity.ok(menuService
                .getAllMenusByRestaurantName(name).stream()
                .map(this::convertToMenuResponse)
                .toList());
    }

    @Override
    public ResponseEntity<List<MenuResponse>> getAllMenus() {
        return ResponseEntity.ok(menuService.getAllMenus().stream()
                .map(this::convertToMenuResponse)
                .toList());
    }

    @Override
    public ResponseEntity<MenuResponse> updateMenu(@PathVariable Long id, @RequestBody @Valid RegisterMenuRequest menuRequestDTO) {
        var updatedMenu = processUpdateMenu(id, menuRequestDTO);
        return ResponseEntity.ok(convertToMenuResponse(updatedMenu));
    }

    @Override
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return ResponseEntity.noContent().build();
    }

    private Menu fetchMenuById(Long id) {
        return menuService.getMenuById(id);
    }

    private Menu processUpdateMenu(Long id, RegisterMenuRequest menuRequestDTO) {
        Menu existingMenuEntity = fetchMenuById(id);
        menuUpdateRequestToMenuConverter.convert(menuRequestDTO, existingMenuEntity);
        return menuService.updateMenu(existingMenuEntity);
    }

    private Menu convertToMenu(RegisterMenuRequest menuRequestDTO) {
        return registerMenuRequestToMenuConverter.convert(menuRequestDTO);
    }

    private MenuResponse convertToMenuResponse(Menu menu) {
        return menuToMenuResponseConverter.convert(menu);
    }
}
