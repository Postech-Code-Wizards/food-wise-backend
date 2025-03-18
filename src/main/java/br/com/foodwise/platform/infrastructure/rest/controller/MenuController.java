package br.com.foodwise.platform.infrastructure.rest.controller;

import br.com.foodwise.platform.application.facade.MenuFacade;
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

    private final MenuFacade menuFacade;

    @Override
    public ResponseEntity<MenuResponse> createMenu(@RequestBody @Valid RegisterMenuRequest menuRequestDTO) {
        var createdMenu = menuFacade.createMenu(menuRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMenu);
    }

    @Override
    public ResponseEntity<MenuResponse> getMenuById(@PathVariable Long id) {
        var menuResponse = menuFacade.getMenuById(id);
        return ResponseEntity.ok(menuResponse);
    }

    @Override
    public ResponseEntity<List<MenuResponse>> getMenusByRestaurantName(@PathVariable String name) {
        return ResponseEntity.ok(menuFacade
                .getAllMenusByRestaurantName(name));
    }

    @Override
    public ResponseEntity<List<MenuResponse>> getAllMenus() {
        return ResponseEntity.ok(menuFacade.getAllMenus());
    }

    @Override
    public ResponseEntity<MenuResponse> updateMenu(@PathVariable Long id, @RequestBody @Valid RegisterMenuRequest menuRequestDTO) {
        var updatedMenu = menuFacade.updateMenu(id, menuRequestDTO);
        return ResponseEntity.ok(updatedMenu);
    }

    @Override
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        menuFacade.deleteMenu(id);
        return ResponseEntity.noContent().build();
    }

}
