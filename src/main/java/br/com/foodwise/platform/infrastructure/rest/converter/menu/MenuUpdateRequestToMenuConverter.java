package br.com.foodwise.platform.infrastructure.rest.converter.menu;

import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menu.RegisterMenuRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class MenuUpdateRequestToMenuConverter {

    public Menu convert(RegisterMenuRequest registerMenuRequest, Menu menu) {
        return new Menu(
                menu.getId(),
                menu.getRestaurantProfile(),
                registerMenuRequest.getName(),
                registerMenuRequest.getDescription(),
                menu.getCreatedAt(),
                ZonedDateTime.now()
        );
    }

}