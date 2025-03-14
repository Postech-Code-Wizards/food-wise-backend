package br.com.foodwise.platform.gateway.database.jpa;

import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.gateway.MenuItemGateway;
import br.com.foodwise.platform.gateway.database.jpa.converter.MenuItemDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.MenuItemEntityToDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.repository.MenuItemRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MenuItemJpaGateway implements MenuItemGateway {

    private final MenuItemRepository menuItemRepository;
    private final MenuItemEntityToDomainConverter menuItemEntityToDomainConverter;
    private final MenuItemDomainToEntityConverter menuItemDomainToEntityConverter;

    @Override
    public MenuItem findById(Long id) {
        var menuEntity = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MENU_ITEM_DOES_NOT_EXIST", ""));

        return menuItemEntityToDomainConverter.convert(menuEntity);
    }

    @Override
    public List<MenuItem> findAll() {
        var menuItemEntityList = menuItemRepository.findAll();

        return menuItemEntityList
                .stream()
                .map(menuItemEntityToDomainConverter::convert)
                .toList();
    }

    @Override
    public List<MenuItem> findMenuItemByName(String itemName) {

        var menuItemEntityList = menuItemRepository.findMenuItemEntityByName(itemName);

        return menuItemEntityList
                .stream()
                .map(menuItemEntityToDomainConverter::convert)
                .toList();
    }

    @Override
    public MenuItem save(MenuItem menuItem) {
        var menuItemEntity = menuItemDomainToEntityConverter.convert(menuItem);
        var menuItemSaved = menuItemRepository.save(menuItemEntity);
        return menuItemEntityToDomainConverter.convert(menuItemSaved);
    }

    @Override
    public void delete(MenuItem menuItem) {
        var menuItemEntity = menuItemDomainToEntityConverter.convert(menuItem);
        menuItemRepository.delete(menuItemEntity);
    }

}
