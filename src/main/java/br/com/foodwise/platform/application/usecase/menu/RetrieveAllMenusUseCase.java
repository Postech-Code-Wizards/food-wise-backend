package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.domain.entities.Menu;
import br.com.foodwise.platform.domain.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@RequiredArgsConstructor

public class RetrieveAllMenusUseCase {
    private final MenuRepository menuRepository;

    public List<Menu> execute() {
        return menuRepository.findAll();
    }
}
