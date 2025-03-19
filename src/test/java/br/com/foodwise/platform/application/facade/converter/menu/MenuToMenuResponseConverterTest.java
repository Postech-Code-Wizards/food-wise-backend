package br.com.foodwise.platform.application.facade.converter.menu;

import br.com.foodwise.platform.application.facade.converter.restaurant.RestaurantProfileDomainToResponseConverter;
import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.MenuResponse;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class MenuToMenuResponseConverterTest {

    @Mock
    private RestaurantProfileDomainToResponseConverter restaurantProfileDomainToResponseConverter;

    @InjectMocks
    private MenuToMenuResponseConverter menuToMenuResponseConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should convert Menu to MenuResponse correctly")
    void convert_ShouldConvertMenuToMenuResponse() {

        Menu source = Instancio.create(Menu.class);
        RestaurantProfileResponse restaurantResponse = Instancio.create(RestaurantProfileResponse.class);

        when(restaurantProfileDomainToResponseConverter.convert(source.getRestaurantProfile()))
                .thenReturn(restaurantResponse);

        MenuResponse result = menuToMenuResponseConverter.convert(source);

        assertNotNull(result);
        assertEquals(source.getId(), result.getId());
        assertEquals(source.getName(), result.getName());
        assertEquals(restaurantResponse, result.getRestaurant());
    }

    @Test
    @DisplayName("Should convert Menu with null RestaurantProfile")
    void convert_ShouldConvertMenuWithNullRestaurantProfile() {

        Menu source = Instancio.of(Menu.class).set(field(Menu::getRestaurantProfile), null).create();

        MenuResponse result = menuToMenuResponseConverter.convert(source);

        assertNotNull(result);
        assertEquals(source.getId(), result.getId());
        assertEquals(source.getName(), result.getName());
        assertNull(result.getRestaurant());
    }

}