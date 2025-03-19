package br.com.foodwise.platform.application.facade.converter.menu;

import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menu.RegisterMenuRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class MenuUpdateRequestToMenuConverterTest {

    @InjectMocks
    private MenuUpdateRequestToMenuConverter menuUpdateRequestToMenuConverter;

    @Test
    @DisplayName("Should convert RegisterMenuRequest to Menu correctly")
    void convert_ShouldConvertRegisterMenuRequestToMenu() {

        RegisterMenuRequest registerMenuRequest = Instancio.create(RegisterMenuRequest.class);
        Menu menu = Instancio.create(Menu.class);

        Menu menuConverted = menuUpdateRequestToMenuConverter.convert(registerMenuRequest, menu);

        assertNotNull(menuConverted);
        assertEquals(registerMenuRequest.getName(), menuConverted.getName());
        assertEquals(registerMenuRequest.getDescription(), menuConverted.getDescription());
        assertNotEquals("Original Menu Name", menuConverted.getName());
        assertNotEquals("Original Menu Description", menuConverted.getDescription());

    }

    @Test
    @DisplayName("Should convert RegisterMenuRequest with null fields")
    void convert_ShouldConvertRegisterMenuRequestWithNullFields() {

        RegisterMenuRequest registerMenuRequest = Instancio.create(RegisterMenuRequest.class);
        Menu menu = Instancio.create(Menu.class);

        Menu menuConverted = menuUpdateRequestToMenuConverter.convert(registerMenuRequest, menu);

        assertNotNull(menuConverted);
        assertEquals(registerMenuRequest.getName(), menuConverted.getName());
        assertEquals(registerMenuRequest.getDescription(), menuConverted.getDescription());

    }
}