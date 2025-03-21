package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.gateway.MenuGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteMenuUseCase {
    private final MenuGateway menuGateway;

    public void execute(Long id) {
        var menu = menuGateway.findById(id);
        menuGateway.delete(menu);
    }
}
