package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.gateway.database.jpa.entities.AddressEntity;
import br.com.foodwise.platform.gateway.database.jpa.entities.PhoneEntity;
import br.com.foodwise.platform.gateway.database.jpa.entities.RestaurantProfileEntity;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
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

class RestaurantProfileDomainToEntityConverterTest {

    @Mock
    private AddressDomainToEntityConverter addressDomainToEntityConverter;

    @Mock
    private PhoneDomainToEntityConverter phoneDomainToEntityConverter;

    @Mock
    private UserDomainToEntityConverter userDomainToEntityConverter;

    @InjectMocks
    private RestaurantProfileDomainToEntityConverter restaurantProfileDomainToEntityConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test conversion of RestaurantProfile to RestaurantProfileEntity")
    public void testConvertRestaurantProfileToEntity() {

        RestaurantProfile source = Instancio.create(RestaurantProfile.class);

        AddressEntity addressEntity = Instancio.create(AddressEntity.class);
        PhoneEntity phoneEntity = Instancio.create(PhoneEntity.class);
        UserEntity userEntity = Instancio.create(UserEntity.class);

        when(addressDomainToEntityConverter.convert(source.getAddress())).thenReturn(addressEntity);
        when(phoneDomainToEntityConverter.convert(source.getPhone())).thenReturn(phoneEntity);
        when(userDomainToEntityConverter.convert(source.getUser())).thenReturn(userEntity);

        RestaurantProfileEntity entity = restaurantProfileDomainToEntityConverter.convert(source);

        assertNotNull(entity);
        assertEquals(source.getBusinessName(), entity.getBusinessName());
        assertEquals(source.getDescription(), entity.getDescription());
        assertEquals(source.getCreatedAt(), entity.getCreatedAt());
        assertEquals(source.getUpdatedAt(), entity.getUpdatedAt());
        assertEquals(addressEntity, entity.getAddressEntity());
        assertEquals(phoneEntity, entity.getPhoneEntity());
        assertEquals(userEntity, entity.getUserEntity());

    }

}