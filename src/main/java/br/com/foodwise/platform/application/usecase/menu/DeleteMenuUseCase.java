package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.domain.repository.MenuRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
public class DeleteMenuUseCase {
    private final MenuRepository menuRepository;

    public void execute(Long id) {
        var menu = menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MENU_DOES_NOT_EXIST", ""));
        menuRepository.delete(menu);
    }
}
