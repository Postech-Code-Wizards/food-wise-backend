package br.com.foodwise.platform.application.usecase.menu.item;

import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.gateway.MenuItemGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RetrieveAllMenusItemUseCase {

    private final MenuItemGateway menuItemGateway;

    public List<MenuItem> execute() {
        return menuItemGateway.findAll();
    }
}
