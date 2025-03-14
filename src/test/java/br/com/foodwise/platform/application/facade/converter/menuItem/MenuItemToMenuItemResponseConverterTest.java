package br.com.foodwise.platform.application.facade.converter.menuItem;

import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.MenuItemResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MenuItemToMenuItemResponseConverterTest {

    @InjectMocks
    private MenuItemToMenuItemResponseConverter menuItemToMenuItemResponseConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test conversion of MenuItem to MenuItemResponse")
    public void testConvertMenuItemToMenuItemResponse() {

        MenuItem source = Instancio.create(MenuItem.class);

        MenuItemResponse response = menuItemToMenuItemResponseConverter.convert(source);

        assertNotNull(response);
        assertEquals(source.getId(), response.getId());
        assertEquals(source.getName(), response.getName());
        assertEquals(source.getDescription(), response.getDescription());
        assertEquals(source.getPrice(), response.getPrice());
        assertEquals(source.getCategory(), response.getCategory());
        assertEquals(source.isAvailable(), response.isAvailable());
        assertEquals(source.getImageUrl(), response.getImageUrl());
    }

}