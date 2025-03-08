package br.com.foodwise.platform.application.facade.converter.menu;

import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menu.RegisterMenuRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterMenuRequestToMenuConverter {

    public Menu convert(RegisterMenuRequest source) {
        return new Menu(
                null,
                null,
                source.getName(),
                source.getDescription(),
                null,
                null
        );
    }

}