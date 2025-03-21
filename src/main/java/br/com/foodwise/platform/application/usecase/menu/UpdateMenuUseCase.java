package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.gateway.MenuGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateMenuUseCase {
    private final MenuGateway menuGateway;

    public Menu execute(Menu menu) {
        return menuGateway.save(menu);
    }
}
