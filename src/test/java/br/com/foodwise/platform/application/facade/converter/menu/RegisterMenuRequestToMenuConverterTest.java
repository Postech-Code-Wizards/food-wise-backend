package br.com.foodwise.platform.application.facade.converter.menu;

import br.com.foodwise.platform.application.facade.converter.menu.RegisterMenuRequestToMenuConverter;
import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menu.RegisterMenuRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RegisterMenuRequestToMenuConverterTest {

    @InjectMocks
    private RegisterMenuRequestToMenuConverter registerMenuRequestToMenuConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should convert RegisterMenuRequest to Menu correctly")
    void convert_ShouldConvertRegisterMenuRequestToMenu() {

        RegisterMenuRequest source = Instancio.create(RegisterMenuRequest.class);

        Menu result = registerMenuRequestToMenuConverter.convert(source);

        assertNull(result.getId());
        assertNull(result.getRestaurantProfile());
        assertEquals(source.getName(), result.getName());
        assertEquals(source.getDescription(), result.getDescription());
        assertNull(result.getCreatedAt());
        assertNull(result.getUpdatedAt());
    }

    @Test
    @DisplayName("Should convert RegisterMenuRequest with null fields")
    void convert_ShouldConvertRegisterMenuRequestWithNullFields() {

        RegisterMenuRequest source = Instancio.of(RegisterMenuRequest.class)
                .set(field(RegisterMenuRequest::getName), null)
                .set(field(RegisterMenuRequest::getDescription), null)
                .create();

        Menu result = registerMenuRequestToMenuConverter.convert(source);

        assertNull(result.getId());
        assertNull(result.getRestaurantProfile());
        assertNull(result.getName());
        assertNull(result.getDescription());
        assertNull(result.getCreatedAt());
        assertNull(result.getUpdatedAt());
    }

}