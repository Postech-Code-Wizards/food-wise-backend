package br.com.foodwise.platform.application.facade.converter.restaurant;

import br.com.foodwise.platform.domain.RestaurantOwner;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RegisterRestaurantOwnerRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class RestaurantOwnerRequestToDomainConverterTest {

    private final RestaurantOwnerRequestToDomainConverter converter = new RestaurantOwnerRequestToDomainConverter();

    @Test
    @DisplayName("Should convert RegisterRestaurantOwnerRequest to RestaurantOwner domain with User")
    void convert_ShouldConvertRequestToDomainWithUser() {

        RegisterRestaurantOwnerRequest source = Instancio.create(RegisterRestaurantOwnerRequest.class);
        User user = Instancio.create(User.class);

        RestaurantOwner result = converter.convert(source, user);

        assertNull(result.getId());
        assertEquals(source.getFirstName(), result.getFirstName());
        assertEquals(source.getLastName(), result.getLastName());
        assertEquals(source.getBusinessRegistrationNumber(), result.getBusinessRegistrationNumber());
        assertEquals(source.getBusinessEmail(), result.getBusinessEmail());
        assertNotNull(result.getCreatedAt());
        assertNotNull(result.getUpdatedAt());
        assertEquals(user, result.getUser());
    }

    @Test
    @DisplayName("Should convert RegisterRestaurantOwnerRequest to RestaurantOwner domain without User")
    void convert_ShouldConvertRequestToDomainWithoutUser() {

        RegisterRestaurantOwnerRequest source = Instancio.create(RegisterRestaurantOwnerRequest.class);

        RestaurantOwner result = converter.convert(source);

        assertNull(result.getId());
        assertEquals(source.getFirstName(), result.getFirstName());
        assertEquals(source.getLastName(), result.getLastName());
        assertEquals(source.getBusinessRegistrationNumber(), result.getBusinessRegistrationNumber());
        assertEquals(source.getBusinessEmail(), result.getBusinessEmail());
        assertNull(result.getCreatedAt());
        assertNull(result.getUpdatedAt());
        assertNull(result.getUser());
    }
}