package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.gateway.entities.MenuEntity;
import br.com.foodwise.platform.gateway.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RetrieveAllMenusUseCase {
    private final MenuRepository menuRepository;

    public List<MenuEntity> execute() {
        return menuRepository.findAll();
    }
}
