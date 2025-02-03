package br.com.foodwise.platform.infrastructure.rest.controller;

import br.com.foodwise.platform.application.service.MenuService;
import br.com.foodwise.platform.domain.entities.Menu;
import br.com.foodwise.platform.infrastructure.rest.converter.menu.MenuToMenuResponseConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.menu.MenuUpdateRequestToMenuConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.menu.RegisterMenuRequestToMenuConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menu.RegisterMenuRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    private final RegisterMenuRequestToMenuConverter registerMenuRequestToMenuConverter;
    private final MenuToMenuResponseConverter menuToMenuResponseConverter;
    private final MenuUpdateRequestToMenuConverter menuUpdateRequestToMenuConverter;

    @PostMapping
    public ResponseEntity<MenuResponse> createMenu(@RequestBody RegisterMenuRequest menuRequestDTO) {
        Menu createdMenu = menuService.createMenu(convertToMenu(menuRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToMenuResponse(createdMenu));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuResponse> getMenuById(@PathVariable Long id) {
        Menu menu = fetchMenuById(id);
        return ResponseEntity.ok(convertToMenuResponse(menu));
    }

    @GetMapping
    public ResponseEntity<List<MenuResponse>> getAllMenus() {
        return ResponseEntity.ok(fetchAllMenus());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuResponse> updateMenu(@PathVariable Long id, @RequestBody RegisterMenuRequest menuRequestDTO) {
        Menu updatedMenu = processUpdateMenu(id, menuRequestDTO);
        return ResponseEntity.ok(convertToMenuResponse(updatedMenu));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return ResponseEntity.noContent().build();
    }

    private Menu fetchMenuById(Long id) {
        return menuService.getMenuById(id);
    }

    private List<MenuResponse> fetchAllMenus() {
        return menuService.getAllMenus().stream()
                .map(this::convertToMenuResponse)
                .toList();
    }

    private Menu processUpdateMenu(Long id, RegisterMenuRequest menuRequestDTO) {
        Menu existingMenu = fetchMenuById(id);
        menuUpdateRequestToMenuConverter.convert(menuRequestDTO, existingMenu);
        return menuService.updateMenu(existingMenu);
    }

    private Menu convertToMenu(RegisterMenuRequest menuRequestDTO) {
        return registerMenuRequestToMenuConverter.convert(menuRequestDTO);
    }

    private MenuResponse convertToMenuResponse(Menu menu) {
        return menuToMenuResponseConverter.convert(menu);
    }
}
