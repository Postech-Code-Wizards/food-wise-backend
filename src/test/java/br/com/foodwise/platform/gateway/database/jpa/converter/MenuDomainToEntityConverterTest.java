package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.gateway.database.jpa.entities.MenuEntity;
import br.com.foodwise.platform.gateway.database.jpa.entities.RestaurantProfileEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MenuDomainToEntityConverterTest {

    @Mock
    private RestaurantProfileDomainToEntityConverter restaurantProfileDomainToEntityConverter;

    @InjectMocks
    private MenuDomainToEntityConverter menuDomainToEntityConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test conversion of Menu to MenuEntity")
    public void testConvertMenuToEntity() {

        Menu source = Instancio.create(Menu.class);
        RestaurantProfileEntity restaurantProfileEntity = Instancio.create(RestaurantProfileEntity.class);

        when(restaurantProfileDomainToEntityConverter.convert(source.getRestaurantProfile())).thenReturn(restaurantProfileEntity);

        MenuEntity entity = menuDomainToEntityConverter.convert(source);

        assertNotNull(entity);
        assertEquals(source.getId(), entity.getId());
        assertEquals(source.getName(), entity.getName());
        assertEquals(source.getDescription(), entity.getDescription());
        assertEquals(source.getCreatedAt(), entity.getCreatedAt());
        assertEquals(source.getUpdatedAt(), entity.getUpdatedAt());
        assertEquals(restaurantProfileEntity, entity.getRestaurantProfileEntity());

    }


}