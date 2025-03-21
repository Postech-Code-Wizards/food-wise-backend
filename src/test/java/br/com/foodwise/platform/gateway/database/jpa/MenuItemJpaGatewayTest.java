package br.com.foodwise.platform.gateway.database.jpa;

import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.gateway.database.jpa.converter.MenuItemDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.MenuItemEntityToDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.MenuItemEntity;
import br.com.foodwise.platform.gateway.database.jpa.repository.MenuItemRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MenuItemJpaGatewayTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private MenuItemEntityToDomainConverter menuItemEntityToDomainConverter;

    @Mock
    private MenuItemDomainToEntityConverter menuItemDomainToEntityConverter;

    @InjectMocks
    private MenuItemJpaGateway menuItemJpaGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should find MenuItem by id")
    void should_find_MenuItem_by_id() {

        Long id = Instancio.create(Long.class);
        MenuItemEntity entity = Instancio.create(MenuItemEntity.class);
        MenuItem domain = Instancio.create(MenuItem.class);

        when(menuItemRepository.findById(id)).thenReturn(Optional.of(entity));
        when(menuItemEntityToDomainConverter.convert(entity)).thenReturn(domain);

        MenuItem result = menuItemJpaGateway.findById(id);

        assertEquals(domain, result);
        verify(menuItemRepository).findById(id);
        verify(menuItemEntityToDomainConverter).convert(entity);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when MenuItem is not found by id")
    void should_throw_ResourceNotFoundException_when_MenuItem_is_not_found_by_id() {

        Long id = Instancio.create(Long.class);

        when(menuItemRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> menuItemJpaGateway.findById(id));
        verify(menuItemRepository).findById(id);
    }

    @Test
    @DisplayName("Should find all MenuItems")
    void should_find_all_MenuItems() {

        List<MenuItemEntity> entityList = Instancio.ofList(MenuItemEntity.class).size(4).create();
        List<MenuItem> domainList = Instancio.ofList(MenuItem.class).size(4).create();

        when(menuItemRepository.findAll()).thenReturn(entityList);
        for (int i = 0; i < entityList.size(); i++) {
            when(menuItemEntityToDomainConverter.convert(entityList.get(i))).thenReturn(domainList.get(i));
        }

        List<MenuItem> result = menuItemJpaGateway.findAll();

        assertEquals(domainList, result);
        verify(menuItemRepository).findAll();
        for (MenuItemEntity entity : entityList) {
            verify(menuItemEntityToDomainConverter).convert(entity);
        }
    }

    @Test
    @DisplayName("Should find MenuItems by item name")
    void should_find_MenuItems_by_item_name() {

        String itemName = Instancio.create(String.class);
        List<MenuItemEntity> entityList = Instancio.ofList(MenuItemEntity.class).size(4).create();
        List<MenuItem> domainList = Instancio.ofList(MenuItem.class).size(4).create();

        when(menuItemRepository.findMenuItemEntityByName(itemName)).thenReturn(entityList);
        for (int i = 0; i < entityList.size(); i++) {
            when(menuItemEntityToDomainConverter.convert(entityList.get(i))).thenReturn(domainList.get(i));
        }

        List<MenuItem> result = menuItemJpaGateway.findMenuItemByName(itemName);

        assertEquals(domainList, result);
        verify(menuItemRepository).findMenuItemEntityByName(itemName);
        for (MenuItemEntity entity : entityList) {
            verify(menuItemEntityToDomainConverter).convert(entity);
        }
    }

    @Test
    @DisplayName("Should save MenuItem")
    void should_save_MenuItem() {

        MenuItem domain = Instancio.create(MenuItem.class);
        MenuItemEntity entity = Instancio.create(MenuItemEntity.class);
        MenuItemEntity savedEntity = Instancio.create(MenuItemEntity.class);
        MenuItem savedDomain = Instancio.create(MenuItem.class);

        when(menuItemDomainToEntityConverter.convert(domain)).thenReturn(entity);
        when(menuItemRepository.save(entity)).thenReturn(savedEntity);
        when(menuItemEntityToDomainConverter.convert(savedEntity)).thenReturn(savedDomain);

        MenuItem result = menuItemJpaGateway.save(domain);

        assertEquals(savedDomain, result);
        verify(menuItemDomainToEntityConverter).convert(domain);
        verify(menuItemRepository).save(entity);
        verify(menuItemEntityToDomainConverter).convert(savedEntity);
    }

    @Test
    @DisplayName("Should delete MenuItem")
    void should_delete_MenuItem() {

        MenuItem domain = Instancio.create(MenuItem.class);
        MenuItemEntity entity = Instancio.create(MenuItemEntity.class);

        when(menuItemDomainToEntityConverter.convert(domain)).thenReturn(entity);

        menuItemJpaGateway.delete(domain);

        verify(menuItemDomainToEntityConverter).convert(domain);
        verify(menuItemRepository).delete(entity);
    }

}