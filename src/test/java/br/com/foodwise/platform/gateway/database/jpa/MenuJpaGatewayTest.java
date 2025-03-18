package br.com.foodwise.platform.gateway.database.jpa;

import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.gateway.database.jpa.converter.MenuDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.MenuEntityToDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.MenuEntity;
import br.com.foodwise.platform.gateway.database.jpa.repository.MenuRepository;
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

class MenuJpaGatewayTest {

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private MenuDomainToEntityConverter menuDomainToEntityConverter;

    @Mock
    private MenuEntityToDomainConverter menuEntityToDomainConverter;

    @InjectMocks
    private MenuJpaGateway menuJpaGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should find Menu by id")
    void should_find_Menu_by_id() {

        long id = Instancio.create(Long.class);
        MenuEntity entity = Instancio.create(MenuEntity.class);
        Menu domain = Instancio.create(Menu.class);

        when(menuRepository.findById(id)).thenReturn(Optional.of(entity));
        when(menuEntityToDomainConverter.convert(entity)).thenReturn(domain);

        Menu result = menuJpaGateway.findById(id);

        assertEquals(domain, result);
        verify(menuRepository).findById(id);
        verify(menuEntityToDomainConverter).convert(entity);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when Menu is not found by id")
    void should_throw_ResourceNotFoundException_when_Menu_is_not_found_by_id() {

        long id = Instancio.create(Long.class);

        when(menuRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> menuJpaGateway.findById(id));
        verify(menuRepository).findById(id);
    }

    @Test
    @DisplayName("Should find all Menus")
    void should_find_all_Menus() {

        List<MenuEntity> entityList = Instancio.ofList(MenuEntity.class).size(4).create();
        List<Menu> domainList = Instancio.ofList(Menu.class).size(4).create();

        when(menuRepository.findAll()).thenReturn(entityList);
        for (int i = 0; i < entityList.size(); i++) {
            when(menuEntityToDomainConverter.convert(entityList.get(i))).thenReturn(domainList.get(i));
        }

        List<Menu> result = menuJpaGateway.findAll();

        assertEquals(domainList, result);
        verify(menuRepository).findAll();
        for (MenuEntity entity : entityList) {
            verify(menuEntityToDomainConverter).convert(entity);
        }
    }

    @Test
    @DisplayName("Should find Menus by restaurant profile business name")
    void should_find_Menus_by_restaurant_profile_business_name() {

        String businessName = Instancio.create(String.class);
        List<MenuEntity> entityList = Instancio.ofList(MenuEntity.class).size(4).create();
        List<Menu> domainList = Instancio.ofList(Menu.class).size(4).create();

        when(menuRepository.findByRestaurantProfileEntityBusinessName(businessName)).thenReturn(entityList);
        for (int i = 0; i < entityList.size(); i++) {
            when(menuEntityToDomainConverter.convert(entityList.get(i))).thenReturn(domainList.get(i));
        }

        List<Menu> result = menuJpaGateway.findByRestaurantProfileEntityBusinessName(businessName);

        assertEquals(domainList, result);
        verify(menuRepository).findByRestaurantProfileEntityBusinessName(businessName);
        for (MenuEntity entity : entityList) {
            verify(menuEntityToDomainConverter).convert(entity);
        }
    }

    @Test
    @DisplayName("Should save Menu")
    void should_save_Menu() {

        Menu domain = Instancio.create(Menu.class);
        MenuEntity entity = Instancio.create(MenuEntity.class);
        MenuEntity savedEntity = Instancio.create(MenuEntity.class);
        Menu savedDomain = Instancio.create(Menu.class);

        when(menuDomainToEntityConverter.convert(domain)).thenReturn(entity);
        when(menuRepository.save(entity)).thenReturn(savedEntity);
        when(menuEntityToDomainConverter.convert(savedEntity)).thenReturn(savedDomain);

        Menu result = menuJpaGateway.save(domain);

        assertEquals(savedDomain, result);
        verify(menuDomainToEntityConverter).convert(domain);
        verify(menuRepository).save(entity);
        verify(menuEntityToDomainConverter).convert(savedEntity);
    }

    @Test
    @DisplayName("Should delete Menu")
    void should_delete_Menu() {

        Menu domain = Instancio.create(Menu.class);
        MenuEntity entity = Instancio.create(MenuEntity.class);

        when(menuDomainToEntityConverter.convert(domain)).thenReturn(entity);

        menuJpaGateway.delete(domain);

        verify(menuDomainToEntityConverter).convert(domain);
        verify(menuRepository).delete(entity);
    }

}