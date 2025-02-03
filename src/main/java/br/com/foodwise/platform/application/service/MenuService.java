package br.com.foodwise.platform.application.service;

import br.com.foodwise.platform.application.usecase.menu.CreateMenuUseCase;
import br.com.foodwise.platform.application.usecase.menu.DeleteMenuUseCase;
import br.com.foodwise.platform.application.usecase.menu.RetrieveAllMenusUseCase;
import br.com.foodwise.platform.application.usecase.menu.RetrieveMenuUseCase;
import br.com.foodwise.platform.application.usecase.menu.UpdateMenuUseCase;
import br.com.foodwise.platform.domain.entities.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final CreateMenuUseCase createMenuUseCase;
    private final RetrieveMenuUseCase retrieveMenuUseCase;
    private final RetrieveAllMenusUseCase retrieveAllMenusUseCase;
    private final UpdateMenuUseCase updateMenuUseCase;
    private final DeleteMenuUseCase deleteMenuUseCase;

    public Menu createMenu(Menu menu) {
        return createMenuUseCase.execute(menu);
    }

    public Menu getMenuById(Long id) {
        return retrieveMenuUseCase.execute(id);
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
