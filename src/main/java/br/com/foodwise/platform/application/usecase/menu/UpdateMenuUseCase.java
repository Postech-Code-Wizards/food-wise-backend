package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.domain.entities.Menu;
import br.com.foodwise.platform.domain.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateMenuUseCase {
    private final MenuRepository menuRepository;

    public Menu execute(Menu menu) {
        return menuRepository.save(menu);
    }
}
