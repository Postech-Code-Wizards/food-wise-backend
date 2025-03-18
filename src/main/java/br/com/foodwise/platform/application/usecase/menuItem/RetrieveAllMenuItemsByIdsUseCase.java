package br.com.foodwise.platform.application.usecase.menuItem;

import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.gateway.MenuItemGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RetrieveAllMenuItemsByIdsUseCase {
    private final MenuItemGateway menuItemGateway;

    public List<MenuItem> retrieveAllById(List<Long> menuItemsIds) {
        return menuItemsIds.stream().map(menuItemGateway::findById).toList();
    }
}
