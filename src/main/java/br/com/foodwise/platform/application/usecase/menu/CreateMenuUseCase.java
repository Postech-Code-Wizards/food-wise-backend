package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.gateway.entities.MenuEntity;
import br.com.foodwise.platform.gateway.entities.UserEntity;
import br.com.foodwise.platform.gateway.repository.MenuRepository;
import br.com.foodwise.platform.gateway.repository.RestaurantProfileRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateMenuUseCase {

    private final MenuRepository menuRepository;
    private final RestaurantProfileRepository restaurantProfileRepository;

    public MenuEntity execute(MenuEntity menuEntity) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (UserEntity) authentication.getPrincipal();

        var restaurantProfile = restaurantProfileRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("RESTAURANT_DOES_NOT_EXIST", ""));
        menuEntity.setRestaurantProfileEntity(restaurantProfile);
        return menuRepository.save(menuEntity);
    }
}
