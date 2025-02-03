package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.domain.entities.Menu;
import br.com.foodwise.platform.domain.repository.MenuRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class RetrieveMenuUseCase {

    private final MenuRepository menuRepository;

    public Menu execute(Long id) {
        return menuRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("MENU_DOES_NOT_EXIST", ""));
    }
}
