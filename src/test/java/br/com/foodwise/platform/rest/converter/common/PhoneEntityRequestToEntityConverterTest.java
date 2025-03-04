package br.com.foodwise.platform.rest.converter.common;

import br.com.foodwise.platform.infrastructure.rest.converter.common.PhoneRequestToDomainConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.PhoneRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.foodwise.platform.factory.RequestFactory.buildPhoneRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class PhoneEntityRequestToEntityConverterTest {

    private PhoneRequestToDomainConverter converter;

    @BeforeEach
    void setUp() {
        converter = new PhoneRequestToDomainConverter();
    }

    @Test
    void convert_shouldMapPhoneRequestToPhoneEntity() {
        var source = buildPhoneRequest();
        var result = converter.convert(source);

        assertNotNull(result);
        assertEquals(source.getPhoneType(), result.getPhoneType());
        assertEquals(source.getAreaCode(), result.getAreaCode());
        assertEquals(source.getPhoneNumber(), result.getPhoneNumber());
    }

    @Test
    void convert_shouldHandleNullFieldsGracefully() {
        var sourceWithNullFields = new PhoneRequest();

        var result = converter.convert(sourceWithNullFields);

        assertNotNull(result);
        assertNull(result.getAreaCode());
        assertNull(result.getPhoneType());
        assertNull(result.getPhoneNumber());
    }

}
