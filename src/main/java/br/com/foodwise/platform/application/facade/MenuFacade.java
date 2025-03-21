package br.com.foodwise.platform.application.facade;

import br.com.foodwise.platform.application.facade.converter.menu.MenuToMenuResponseConverter;
import br.com.foodwise.platform.application.facade.converter.menu.MenuUpdateRequestToMenuConverter;
import br.com.foodwise.platform.application.facade.converter.menu.RegisterMenuRequestToMenuConverter;
import br.com.foodwise.platform.application.usecase.menu.CreateMenuUseCase;
import br.com.foodwise.platform.application.usecase.menu.DeleteMenuUseCase;
import br.com.foodwise.platform.application.usecase.menu.RetrieveAllMenusByRestaurantNameUseCase;
import br.com.foodwise.platform.application.usecase.menu.RetrieveAllMenusUseCase;
import br.com.foodwise.platform.application.usecase.menu.RetrieveMenuUseCase;
import br.com.foodwise.platform.application.usecase.menu.UpdateMenuUseCase;
import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menu.RegisterMenuRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuFacade {

    private final CreateMenuUseCase createMenuUseCase;
    private final RetrieveMenuUseCase retrieveMenuUseCase;
    private final RetrieveAllMenusUseCase retrieveAllMenusUseCase;
    private final RetrieveAllMenusByRestaurantNameUseCase retrieveAllMenusByRestaurantNameUseCase;
    private final UpdateMenuUseCase updateMenuUseCase;
    private final DeleteMenuUseCase deleteMenuUseCase;
    private final RegisterMenuRequestToMenuConverter registerMenuRequestToMenuConverter;
    private final MenuToMenuResponseConverter menuToMenuResponseConverter;
    private final MenuUpdateRequestToMenuConverter menuUpdateRequestToMenuConverter;

    public MenuResponse createMenu(RegisterMenuRequest menuRequestDTO) {
        var menuConverted = registerMenuRequestToMenuConverter.convert(menuRequestDTO);
        var menu = createMenuUseCase.execute(menuConverted);
        return convertToMenuResponse(menu);
    }

    public MenuResponse getMenuById(Long id) {
        Menu menu = retrieveMenuUseCase.execute(id);
        return convertToMenuResponse(menu);
    }

    public List<MenuResponse> getAllMenusByRestaurantName(String restaurantName) {
        List<Menu> menuList = retrieveAllMenusByRestaurantNameUseCase.execute(restaurantName);
        return menuList.stream().map(this::convertToMenuResponse).toList();
    }

    public List<MenuResponse> getAllMenus() {

        List<Menu> menuList = retrieveAllMenusUseCase.execute();
        return menuList.stream().map(this::convertToMenuResponse).toList();
    }

    public MenuResponse updateMenu(Long id, RegisterMenuRequest menuRequestDTO) {
        Menu existingMenu = retrieveMenuUseCase.execute(id);
        Menu menu = menuUpdateRequestToMenuConverter.convert(menuRequestDTO, existingMenu);
        return convertToMenuResponse(updateMenuUseCase.execute(menu));
    }

    public void deleteMenu(Long id) {
        deleteMenuUseCase.execute(id);
    }

    private MenuResponse convertToMenuResponse(Menu menu) {
        return menuToMenuResponseConverter.convert(menu);
    }
}
