package br.com.foodwise.platform.gateway.database.jpa;

import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.gateway.MenuGateway;
import br.com.foodwise.platform.gateway.database.jpa.converter.MenuDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.MenuEntityToDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.MenuEntity;
import br.com.foodwise.platform.gateway.database.jpa.repository.MenuRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class MenuJpaGateway implements MenuGateway {

    private final MenuRepository menuRepository;
    private final MenuDomainToEntityConverter menuDomainToEntityConverter;
    private final MenuEntityToDomainConverter menuEntityToDomainConverter;

    @Override
    public Menu findById(long id) {
        var menuEntity = menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MENU_DOES_NOT_EXIST", ""));

        return menuEntityToDomainConverter.convert(menuEntity);
    }

    @Override
    public List<Menu> findAll() {
        var menuEntityList = menuRepository.findAll();

        return menuEntityList.stream()
                .map(menuEntityToDomainConverter::convert)
                .toList();
    }

    @Override
    public List<Menu> findByRestaurantProfileEntityBusinessName(String businessName) {
        var menuEntityList = menuRepository.findByRestaurantProfileEntityBusinessName(businessName);

        return menuEntityList.stream()
                .map(menuEntityToDomainConverter::convert)
                .toList();
    }

    @Override
    public Menu save(Menu menu) {
        MenuEntity menuEntity = menuDomainToEntityConverter.convert(menu);
        MenuEntity menuSaved = menuRepository.save(menuEntity);
        return menuEntityToDomainConverter.convert(menuSaved);

    }

    @Override
    public void delete(Menu menu) {
        if(Objects.isNull(menu)) {
            log.info("Menu object in delete action is null");
        }

        var menuEntity = menuDomainToEntityConverter.convert(menu);
        menuRepository.delete(menuEntity);
    }

}
