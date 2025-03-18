package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.gateway.MenuGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class RetrieveAllMenusByRestaurantNameUseCase {
    private final MenuGateway menuGateway;

    public List<Menu> execute(String businessName) {
        return menuGateway.findByRestaurantProfileEntityBusinessName(businessName);
    }
}
