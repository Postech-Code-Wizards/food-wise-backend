package br.com.foodwise.platform.infrastructure.rest.converter.restaurant;

import br.com.foodwise.platform.domain.Address;
import br.com.foodwise.platform.domain.Phone;
import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.infrastructure.rest.converter.common.AddressRequestToDomainConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.common.PhoneRequestToDomainConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.common.UserRequestToDomainConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class RestaurantProfileRequestToDomainConverterTest {

    @Mock
    private AddressRequestToDomainConverter addressRequestToDomainConverter;

    @Mock
    private PhoneRequestToDomainConverter phoneRequestToDomainConverter;

    @Mock
    private UserRequestToDomainConverter userRequestToDomainConverter;

    @InjectMocks
    private RestaurantProfileRequestToDomainConverter restaurantProfileRequestToDomainConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should convert RestaurantProfileRequest to RestaurantProfile with User")
    void convert_ShouldConvertRestaurantProfileRequestToRestaurantProfileWithUser() {

        RestaurantProfileRequest source = Instancio.create(RestaurantProfileRequest.class);
        User user = Instancio.create(User.class);
        Address address = Instancio.create(Address.class);
        Phone phone = Instancio.create(Phone.class);

        when(addressRequestToDomainConverter.convert(source.getAddress())).thenReturn(address);
        when(phoneRequestToDomainConverter.convert(source.getPhone())).thenReturn(phone);

        RestaurantProfile result = restaurantProfileRequestToDomainConverter.convert(source, user);

        assertNull(result.getId());
        assertEquals(source.getBusinessName(), result.getBusinessName());
        assertEquals(source.getDescription(), result.getDescription());
        assertEquals(source.getBusinessHours(), result.getBusinessHours());
        assertEquals(source.getDeliveryRadius(), result.getDeliveryRadius());
        assertEquals(source.getCuisineType(), result.getCuisineType());
        assertNull(result.getCreatedAt());
        assertNull(result.getUpdatedAt());
        assertEquals(user, result.getUser());
        assertEquals(address, result.getAddress());
        assertEquals(phone, result.getPhone());
    }

    @Test
    @DisplayName("Should convert RestaurantProfileRequest to RestaurantProfile without User")
    void convert_ShouldConvertRestaurantProfileRequestToRestaurantProfileWithoutUser() {

        RestaurantProfileRequest source = Instancio.create(RestaurantProfileRequest.class);
        Address address = Instancio.create(Address.class);
        Phone phone = Instancio.create(Phone.class);

        when(addressRequestToDomainConverter.convert(source.getAddress())).thenReturn(address);
        when(phoneRequestToDomainConverter.convert(source.getPhone())).thenReturn(phone);

        RestaurantProfile result = restaurantProfileRequestToDomainConverter.convert(source);

        assertNull(result.getId());
        assertEquals(source.getBusinessName(), result.getBusinessName());
        assertEquals(source.getDescription(), result.getDescription());
        assertEquals(source.getBusinessHours(), result.getBusinessHours());
        assertEquals(source.getDeliveryRadius(), result.getDeliveryRadius());
        assertEquals(source.getCuisineType(), result.getCuisineType());
        assertNull(result.getCreatedAt());
        assertNull(result.getUpdatedAt());
        assertNull(result.getUser());
        assertEquals(address, result.getAddress());
        assertEquals(phone, result.getPhone());
    }

}