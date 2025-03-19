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

class PhoneEntityToDomainConverterTest {

    @InjectMocks
    private PhoneEntityToDomainConverter phoneEntityToDomainConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test conversion of PhoneEntity to Phone")
    public void testConvertPhoneEntityToDomain() {

        PhoneEntity source = Instancio.create(PhoneEntity.class);

        Phone domain = phoneEntityToDomainConverter.convert(source);

        assertNotNull(domain);
        assertEquals(source.getId(), domain.getId());
        assertEquals(source.getCreatedAt(), domain.getCreatedAt());
        assertEquals(source.getUpdatedAt(), domain.getUpdatedAt());
    }

}