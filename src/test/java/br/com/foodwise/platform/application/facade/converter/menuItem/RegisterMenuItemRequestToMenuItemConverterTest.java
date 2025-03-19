package br.com.foodwise.platform.application.facade.converter.menuItem;

import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menuItem.RegisterMenuItemRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RegisterMenuItemRequestToMenuItemConverterTest {

    @InjectMocks
    private RegisterMenuItemRequestToMenuItemConverter registerMenuItemRequestToMenuItemConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test conversion of RegisterMenuItemRequest to MenuItem")
    void testConvertRegisterMenuItemRequestToMenuItem() {

        RegisterMenuItemRequest source = Instancio.create(RegisterMenuItemRequest.class);
        Menu menu = Instancio.create(Menu.class);

        MenuItem convertedMenuItem = registerMenuItemRequestToMenuItemConverter.convert(source, menu);

        assertNotNull(convertedMenuItem);
        assertEquals(source.getName(), convertedMenuItem.getName());
        assertEquals(source.getDescription(), convertedMenuItem.getDescription());
        assertEquals(source.getPrice(), convertedMenuItem.getPrice());
        assertEquals(source.getCategory(), convertedMenuItem.getCategory());
        assertEquals(source.isAvailable(), convertedMenuItem.isAvailable());
        assertEquals(source.getImageUrl(), convertedMenuItem.getImageUrl());
        assertEquals(menu, convertedMenuItem.getMenu());
        assertNotNull(convertedMenuItem.getCreatedAt());
        assertNotNull(convertedMenuItem.getUpdatedAt());
    }

}