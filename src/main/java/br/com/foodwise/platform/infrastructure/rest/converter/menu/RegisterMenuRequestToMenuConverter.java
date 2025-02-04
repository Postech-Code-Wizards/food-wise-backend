package br.com.foodwise.platform.infrastructure.rest.converter.menu;

import br.com.foodwise.platform.domain.entities.Menu;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menu.RegisterMenuRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterMenuRequestToMenuConverter implements Converter<RegisterMenuRequest, Menu> {

    @Override
    public Menu convert(RegisterMenuRequest source) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(source, Menu.class);
    }

}