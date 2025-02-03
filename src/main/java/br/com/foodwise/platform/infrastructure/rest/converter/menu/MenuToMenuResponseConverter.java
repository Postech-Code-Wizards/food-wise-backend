package br.com.foodwise.platform.infrastructure.rest.converter.menu;

import br.com.foodwise.platform.domain.entities.Menu;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuToMenuResponseConverter implements Converter<Menu, MenuResponse> {

    @Override
    public MenuResponse convert(Menu source) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(source, MenuResponse.class);
    }
}