package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.Address;
import br.com.foodwise.platform.gateway.database.jpa.entities.AddressEntity;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(InstancioExtension.class)
public class AddressDomainToEntityConverterTest {

    @InjectMocks
    private AddressDomainToEntityConverter addressDomainToEntityConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test conversion of Address domain object to AddressEntity")
    public void testConvertAddressDomainToEntity() {

        Address source = Instancio.create(Address.class);
        AddressEntity entity = addressDomainToEntityConverter.convert(source);

        assertNotNull(entity);
        assertEquals(source.getStreet(), entity.getStreet());
        assertEquals(source.getCity(), entity.getCity());
        assertEquals(source.getState(), entity.getState());
    }
}