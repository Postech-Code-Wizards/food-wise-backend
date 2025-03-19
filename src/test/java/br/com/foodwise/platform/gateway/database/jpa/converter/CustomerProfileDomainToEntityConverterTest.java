package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.CustomerProfile;
import br.com.foodwise.platform.gateway.database.jpa.entities.AddressEntity;
import br.com.foodwise.platform.gateway.database.jpa.entities.CustomerProfileEntity;
import br.com.foodwise.platform.gateway.database.jpa.entities.PhoneEntity;
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

class CustomerProfileDomainToEntityConverterTest {

    @Mock
    private AddressDomainToEntityConverter addressDomainToEntityConverter;

    @Mock
    private PhoneDomainToEntityConverter phoneDomainToEntityConverter;

    @Mock
    private UserDomainToEntityConverter userDomainToEntityConverter;

    @InjectMocks
    private CustomerProfileDomainToEntityConverter converter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test conversion of CustomerProfile to CustomerProfileEntity")
    public void testConvertCustomerProfileToEntity() {

        CustomerProfile source = Instancio.create(CustomerProfile.class);
        AddressEntity addressEntity = Instancio.create(AddressEntity.class);
        PhoneEntity phoneEntity = Instancio.create(PhoneEntity.class);
        UserEntity userEntity = Instancio.create(UserEntity.class);

        when(addressDomainToEntityConverter.convert(source.getAddress())).thenReturn(addressEntity);
        when(phoneDomainToEntityConverter.convert(source.getPhone())).thenReturn(phoneEntity);
        when(userDomainToEntityConverter.convert(source.getUser())).thenReturn(userEntity);

        CustomerProfileEntity entity = converter.convert(source);

        assertNotNull(entity);
        assertEquals(source.getFirstName(), entity.getFirstName());
        assertEquals(source.getLastName(), entity.getLastName());
        assertEquals(addressEntity, entity.getAddressEntity());
        assertEquals(source.getCreatedAt(), entity.getCreatedAt());
        assertEquals(source.getUpdatedAt(), entity.getUpdatedAt());
        assertEquals(userEntity, entity.getUserEntity());
        assertEquals(phoneEntity, entity.getPhoneEntity());
    }

}