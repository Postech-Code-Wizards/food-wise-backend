package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.gateway.database.jpa.entities.MenuEntity;
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

class MenuEntityToDomainConverterTest {

    @Mock
    private RestaurantProfileEntityToDomainConverter restaurantProfileEntityToDomainConverter;

    @InjectMocks
    private MenuEntityToDomainConverter menuEntityToDomainConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test conversion of MenuEntity to Menu")
    void testConvertMenuEntityToDomain() {

        MenuEntity source = Instancio.create(MenuEntity.class);
        RestaurantProfile restaurantProfile = Instancio.create(RestaurantProfile.class);

        when(restaurantProfileEntityToDomainConverter.convert(source.getRestaurantProfileEntity())).thenReturn(restaurantProfile);

        Menu domain = menuEntityToDomainConverter.convert(source);

        assertNotNull(domain);
        assertEquals(source.getId(), domain.getId());
        assertEquals(restaurantProfile, domain.getRestaurantProfile());
        assertEquals(source.getName(), domain.getName());
        assertEquals(source.getDescription(), domain.getDescription());
        assertEquals(source.getCreatedAt(), domain.getCreatedAt());
        assertEquals(source.getUpdatedAt(), domain.getUpdatedAt());

    }

}