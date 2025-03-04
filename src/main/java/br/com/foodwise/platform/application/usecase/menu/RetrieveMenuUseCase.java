package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.gateway.MenuGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveMenuUseCase {

    private final MenuGateway menuGateway;

    public Menu execute(Long id) {
        return menuGateway.findById(id);
    }
}
