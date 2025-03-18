package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.RestaurantOwner;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.gateway.database.jpa.entities.RestaurantOwnerEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RestaurantOwnerEntityToDomainConverterTest {

    @Mock
    private UserEntityToDomainConverter userEntityToDomainConverter;

    @InjectMocks
    private RestaurantOwnerEntityToDomainConverter converter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should convert RestaurantOwnerEntity to RestaurantOwner domain")
    void convert_ShouldConvertEntityToDomain() {

        RestaurantOwnerEntity source = Instancio.create(RestaurantOwnerEntity.class);
        User user = Instancio.create(User.class);

        when(userEntityToDomainConverter.convert(source.getUserEntity())).thenReturn(user);

        RestaurantOwner result = converter.convert(source);

        assertEquals(source.getId(), result.getId());
        assertEquals(source.getFirstName(), result.getFirstName());
        assertEquals(source.getLastName(), result.getLastName());
        assertEquals(source.getBusinessRegistrationNumber(), result.getBusinessRegistrationNumber());
        assertEquals(source.getBusinessEmail(), result.getBusinessEmail());
        assertEquals(source.getCreatedAt(), result.getCreatedAt());
        assertEquals(source.getUpdatedAt(), result.getUpdatedAt());
        assertEquals(user, result.getUser());
    }

}