package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.gateway.entities.MenuEntity;
import br.com.foodwise.platform.gateway.repository.MenuRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveMenuUseCase {

    private final MenuRepository menuRepository;

    public MenuEntity execute(Long id) {
        return menuRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("MENU_DOES_NOT_EXIST", ""));
    }
}
