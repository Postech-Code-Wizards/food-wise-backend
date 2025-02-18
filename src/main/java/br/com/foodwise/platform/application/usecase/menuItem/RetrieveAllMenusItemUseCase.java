package br.com.foodwise.platform.application.usecase.menuItem;

import br.com.foodwise.platform.domain.entities.MenuItem;
import br.com.foodwise.platform.domain.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RetrieveAllMenusItemUseCase {

    private final MenuItemRepository menuItemRepository;

    public List<MenuItem> execute() {
        return menuItemRepository.findAll();
    }
}
