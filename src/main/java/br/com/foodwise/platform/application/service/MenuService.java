package br.com.foodwise.platform.application.service;

import br.com.foodwise.platform.application.usecase.menu.*;
import br.com.foodwise.platform.domain.Menu;
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

    public Menu createMenu(Menu menu) {
        return createMenuUseCase.execute(menu);
    }

    public Menu getMenuById(Long id) {
        return retrieveMenuUseCase.execute(id);
    }

    public List<Menu> getAllMenusByRestaurantName(String restaurantName) {
        return retrieveAllMenusByRestaurantNameUseCase.execute(restaurantName);
    }

    public List<Menu> getAllMenus() {
        return retrieveAllMenusUseCase.execute();
    }

    public Menu updateMenu(Menu menu) {
        return updateMenuUseCase.execute(menu);
    }

    public void deleteMenu(Long id) {
        deleteMenuUseCase.execute(id);
    }
}
