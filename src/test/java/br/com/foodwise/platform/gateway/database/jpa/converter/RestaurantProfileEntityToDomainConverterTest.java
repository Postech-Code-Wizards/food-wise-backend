package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.Address;
import br.com.foodwise.platform.domain.Phone;
import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.gateway.database.jpa.entities.RestaurantProfileEntity;
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

class RestaurantProfileEntityToDomainConverterTest {

    @Mock
    private AddressEntityToDomainConverter addressEntityToDomainConverter;

    @Mock
    private UserEntityToDomainConverter userEntityToDomainConverter;

    @Mock
    private PhoneEntityToDomainConverter phoneEntityToDomainConverter;

    @InjectMocks
    private RestaurantProfileEntityToDomainConverter restaurantProfileEntityToDomainConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test conversion of RestaurantProfileEntity to RestaurantProfile")
    public void testConvertRestaurantProfileEntityToDomain() {

        RestaurantProfileEntity source = Instancio.create(RestaurantProfileEntity.class);
        Address address = Instancio.create(Address.class);
        User user = Instancio.create(User.class);
        Phone phone = Instancio.create(Phone.class);

        when(addressEntityToDomainConverter.convert(source.getAddressEntity())).thenReturn(address);
        when(userEntityToDomainConverter.convert(source.getUserEntity())).thenReturn(user);
        when(phoneEntityToDomainConverter.convert(source.getPhoneEntity())).thenReturn(phone);

        RestaurantProfile domain = restaurantProfileEntityToDomainConverter.convert(source);

        assertNotNull(domain);
        assertEquals(source.getBusinessName(), domain.getBusinessName());
        assertEquals(source.getDescription(), domain.getDescription());
        assertEquals(source.getBusinessHours(), domain.getBusinessHours());
        assertEquals(source.getDeliveryRadius(), domain.getDeliveryRadius());
        assertEquals(source.getCuisineType(), domain.getCuisineType());
        assertEquals(source.isOpen(), domain.isOpen());
        assertEquals(source.getCreatedAt(), domain.getCreatedAt());
        assertEquals(source.getUpdatedAt(), domain.getUpdatedAt());
        assertEquals(user, domain.getUser());
        assertEquals(address, domain.getAddress());
        assertEquals(phone, domain.getPhone());

    }

}