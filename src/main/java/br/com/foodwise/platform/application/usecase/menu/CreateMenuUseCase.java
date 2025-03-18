package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.gateway.MenuGateway;
import br.com.foodwise.platform.gateway.RestaurantProfileGateway;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class CreateMenuUseCase {

    private final MenuGateway menuGateway;
    private final RestaurantProfileGateway restaurantProfileGateway;

    public Menu execute(Menu menu) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (UserEntity) authentication.getPrincipal();

        var restaurantProfile = restaurantProfileGateway.findById(user.getId());
        var newMenu = populate(menu, restaurantProfile);
        return menuGateway.save(newMenu);
    }

    private static Menu populate(Menu menu, RestaurantProfile restaurantProfile) {
        return new Menu(
                menu.getId(),
                restaurantProfile,
                menu.getName(),
                menu.getDescription(),
                ZonedDateTime.now(),
                ZonedDateTime.now()
        );
    }
}
