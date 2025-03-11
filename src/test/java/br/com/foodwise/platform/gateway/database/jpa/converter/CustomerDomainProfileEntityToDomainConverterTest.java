package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.Address;
import br.com.foodwise.platform.domain.CustomerProfile;
import br.com.foodwise.platform.domain.Phone;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.gateway.database.jpa.entities.CustomerProfileEntity;
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

class CustomerDomainProfileEntityToDomainConverterTest {

    @Mock
    private AddressEntityToDomainConverter addressEntityToDomainConverter;

    @Mock
    private UserEntityToDomainConverter userEntityToDomainConverter;

    @Mock
    private PhoneEntityToDomainConverter phoneEntityToDomainConverter;

    @InjectMocks
    private CustomerDomainProfileEntityToDomainConverter customerDomainProfileEntityToDomainConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test conversion of CustomerProfileEntity to CustomerProfile domain object")
    public void testConvertCustomerProfileEntityToDomain() {

        CustomerProfileEntity source = Instancio.create(CustomerProfileEntity.class);
        Address address = Instancio.create(Address.class);
        User user = Instancio.create(User.class);
        Phone phone = Instancio.create(Phone.class);

        when(addressEntityToDomainConverter.convert(source.getAddressEntity())).thenReturn(address);
        when(userEntityToDomainConverter.convert(source.getUserEntity())).thenReturn(user);
        when(phoneEntityToDomainConverter.convert(source.getPhoneEntity())).thenReturn(phone);

        CustomerProfile domain = customerDomainProfileEntityToDomainConverter.convert(source);

        assertNotNull(domain);
        assertEquals(source.getFirstName(), domain.getFirstName());
        assertEquals(source.getLastName(), domain.getLastName());
    }

}