package br.com.foodwise.platform.infrastructure.rest.converter.menu;

import br.com.foodwise.platform.gateway.entities.MenuEntity;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menu.RegisterMenuRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuUpdateRequestToMenuConverter {

    public void convert(RegisterMenuRequest source, MenuEntity target) {
        var modelMapper = new ModelMapper();
        modelMapper.map(source, target);
    }
}