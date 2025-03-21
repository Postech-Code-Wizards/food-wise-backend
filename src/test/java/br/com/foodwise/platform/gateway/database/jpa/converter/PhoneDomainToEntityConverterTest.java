package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.Phone;
import br.com.foodwise.platform.gateway.database.jpa.entities.PhoneEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PhoneDomainToEntityConverterTest {

    @InjectMocks
    private PhoneDomainToEntityConverter phoneDomainToEntityConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test conversion of Phone to PhoneEntity")
    void testConvertPhoneToEntity() {

        Phone source = Instancio.create(Phone.class);

        PhoneEntity entity = phoneDomainToEntityConverter.convert(source);

        assertNotNull(entity);
        assertEquals(source.getId(), entity.getId());
    }

}