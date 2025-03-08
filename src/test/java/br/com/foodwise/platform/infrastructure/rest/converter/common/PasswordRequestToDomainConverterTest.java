package br.com.foodwise.platform.infrastructure.rest.converter.common;

import br.com.foodwise.platform.application.facade.converter.common.PasswordRequestToDomainConverter;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.PasswordRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class PasswordRequestToDomainConverterTest {

    @InjectMocks
    private PasswordRequestToDomainConverter passwordRequestToDomainConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should convert PasswordRequest to User correctly")
    void convert_ShouldConvertPasswordRequestToUser() {

        PasswordRequest source = Instancio.create(PasswordRequest.class);

        User result = passwordRequestToDomainConverter.convert(source);

        assertNull(result.getId());
        assertNull(result.getEmail());
        assertEquals(source.getPassword(), result.getPassword());
        assertNull(result.getUserType());
        assertFalse(result.isActive());
        assertNull(result.getCreatedAt());
        assertNull(result.getUpdatedAt());
    }

    @Test
    @DisplayName("Must convert PasswordRequest with null password")
    void convert_ShouldConvertPasswordRequestWithNullPassword() {

        PasswordRequest source = new PasswordRequest();

        User result = passwordRequestToDomainConverter.convert(source);

        assertNull(result.getId());
        assertNull(result.getEmail());
        assertNull(result.getPassword());
        assertNull(result.getUserType());
        assertFalse(result.isActive());
        assertNull(result.getCreatedAt());
        assertNull(result.getUpdatedAt());
    }

}