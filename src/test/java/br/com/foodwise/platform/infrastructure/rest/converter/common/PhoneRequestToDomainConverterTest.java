package br.com.foodwise.platform.infrastructure.rest.converter.common;

import br.com.foodwise.platform.domain.Phone;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.PhoneRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PhoneRequestToDomainConverterTest {


    @InjectMocks
    private PhoneRequestToDomainConverter phoneRequestToDomainConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should convert PhoneRequest to Phone correctly")
    void convert_ShouldConvertPhoneRequestToPhone() {

        PhoneRequest source = Instancio.create(PhoneRequest.class);

        Phone result = phoneRequestToDomainConverter.convert(source);

        assertNull(result.getId());
        assertEquals(source.getAreaCode(), result.getAreaCode());
        assertEquals(source.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(source.getPhoneType(), result.getPhoneType());
        assertNull(result.getCreatedAt());
        assertNull(result.getUpdatedAt());
    }

    @Test
    @DisplayName("Should convert PhoneRequest with null fields")
    void convert_ShouldConvertPhoneRequestWithNullFields() {

        PhoneRequest source = new PhoneRequest();

        Phone result = phoneRequestToDomainConverter.convert(source);

        assertNull(result.getId());
        assertNull(result.getAreaCode());
        assertNull(result.getPhoneNumber());
        assertNull(result.getPhoneType());
        assertNull(result.getCreatedAt());
        assertNull(result.getUpdatedAt());
    }

}