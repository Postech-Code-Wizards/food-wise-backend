package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.domain.entities.Menu;
import br.com.foodwise.platform.domain.entities.User;
import br.com.foodwise.platform.domain.repository.MenuRepository;
import br.com.foodwise.platform.domain.repository.RestaurantProfileRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateMenuUseCase {

    private final MenuRepository menuRepository;
    private final RestaurantProfileRepository restaurantProfileRepository;

    public Menu execute(Menu menu) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (User) authentication.getPrincipal();

        var restaurantProfile = restaurantProfileRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("RESTAURANT_DOES_NOT_EXIST", ""));
        menu.setRestaurantProfile(restaurantProfile);
        return menuRepository.save(menu);
    }
}
