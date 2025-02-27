package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.gateway.entities.MenuEntity;
import br.com.foodwise.platform.gateway.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateMenuUseCase {
    private final MenuRepository menuRepository;

    public MenuEntity execute(MenuEntity menuEntity) {
        return menuRepository.save(menuEntity);
    }
}
