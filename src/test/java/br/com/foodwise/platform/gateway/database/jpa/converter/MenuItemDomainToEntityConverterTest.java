package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.gateway.database.jpa.entities.MenuEntity;
import br.com.foodwise.platform.gateway.database.jpa.entities.MenuItemEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MenuItemDomainToEntityConverterTest {

    @Mock
    private MenuDomainToEntityConverter menuDomainToEntityConverter;

    @InjectMocks
    private MenuItemDomainToEntityConverter menuItemDomainToEntityConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test conversion of MenuItem to MenuItemEntity")
    public void testConvertMenuItemToEntity() {

        MenuItem source = Instancio.create(MenuItem.class);
        MenuEntity menuEntity = Instancio.create(MenuEntity.class);

        when(menuDomainToEntityConverter.convert(source.getMenu())).thenReturn(menuEntity);

        MenuItemEntity entity = menuItemDomainToEntityConverter.convert(source);

        assertNotNull(entity);
        assertEquals(source.getId(), entity.getId());
        assertEquals(source.getName(), entity.getName());
        assertEquals(source.getDescription(), entity.getDescription());
        assertEquals(source.getPrice(), entity.getPrice());
        assertEquals(source.getCreatedAt(), entity.getCreatedAt());
        assertEquals(source.getUpdatedAt(), entity.getUpdatedAt());
        assertEquals(menuEntity, entity.getMenuEntity());

    }

}