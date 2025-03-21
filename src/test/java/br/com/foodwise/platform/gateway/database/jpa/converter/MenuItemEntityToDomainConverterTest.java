package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.gateway.database.jpa.entities.MenuItemEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class MenuItemEntityToDomainConverterTest {

    @Mock
    private MenuEntityToDomainConverter menuEntityToDomainConverter;

    @InjectMocks
    private MenuItemEntityToDomainConverter menuItemEntityToDomainConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test conversion of MenuItemEntity to MenuItem")
    void testConvertMenuItemEntityToDomain() {

        MenuItemEntity source = Instancio.create(MenuItemEntity.class);
        Menu menu = Instancio.create(Menu.class);

        when(menuEntityToDomainConverter.convert(source.getMenuEntity())).thenReturn(menu);

        MenuItem domain = menuItemEntityToDomainConverter.convert(source);

        assertNotNull(domain);
        assertEquals(source.getId(), domain.getId());
        assertEquals(source.getName(), domain.getName());
        assertEquals(source.getDescription(), domain.getDescription());
        assertEquals(source.getPrice(), domain.getPrice());
        assertEquals(source.getCategory(), domain.getCategory());
        assertEquals(source.isAvailable(), domain.isAvailable());
        assertEquals(source.getImageUrl(), domain.getImageUrl());
        assertEquals(source.getCreatedAt(), domain.getCreatedAt());
        assertEquals(source.getUpdatedAt(), domain.getUpdatedAt());
        assertEquals(menu, domain.getMenu());
    }

}