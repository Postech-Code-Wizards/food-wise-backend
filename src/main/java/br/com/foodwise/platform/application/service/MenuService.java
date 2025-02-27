package br.com.foodwise.platform.application.service;

import br.com.foodwise.platform.application.usecase.menu.CreateMenuUseCase;
import br.com.foodwise.platform.application.usecase.menu.DeleteMenuUseCase;
import br.com.foodwise.platform.application.usecase.menu.RetrieveAllMenusByRestaurantNameUseCase;
import br.com.foodwise.platform.application.usecase.menu.RetrieveAllMenusUseCase;
import br.com.foodwise.platform.application.usecase.menu.RetrieveMenuUseCase;
import br.com.foodwise.platform.application.usecase.menu.UpdateMenuUseCase;
import br.com.foodwise.platform.gateway.entities.MenuEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final CreateMenuUseCase createMenuUseCase;
    private final RetrieveMenuUseCase retrieveMenuUseCase;
    private final RetrieveAllMenusUseCase retrieveAllMenusUseCase;
    private final RetrieveAllMenusByRestaurantNameUseCase retrieveAllMenusByRestaurantNameUseCase;
    private final UpdateMenuUseCase updateMenuUseCase;
    private final DeleteMenuUseCase deleteMenuUseCase;

    public MenuEntity createMenu(MenuEntity menuEntity) {
        return createMenuUseCase.execute(menuEntity);
    }

    public MenuEntity getMenuById(Long id) {
        return retrieveMenuUseCase.execute(id);
    }

    public List<MenuEntity> getAllMenusByRestaurantName(String restaurantName) {
        return retrieveAllMenusByRestaurantNameUseCase.execute(restaurantName);
    }

    public List<MenuEntity> getAllMenus() {
        return retrieveAllMenusUseCase.execute();
    }

    public MenuEntity updateMenu(MenuEntity menuEntity) {
        return updateMenuUseCase.execute(menuEntity);
    }

    public void deleteMenu(Long id) {
        deleteMenuUseCase.execute(id);
    }
}
