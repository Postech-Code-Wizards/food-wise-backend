package br.com.foodwise.platform.infrastructure.rest.converter.common;

import br.com.foodwise.platform.application.facade.converter.common.UserRequestToDomainConverter;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class UserRequestToDomainConverterTest {

    @InjectMocks
    private UserRequestToDomainConverter userRequestToDomainConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should convert UserRequest to User correctly")
    void convert_ShouldConvertUserRequestToUser() {

        UserRequest source = Instancio.create(UserRequest.class);

        User result = userRequestToDomainConverter.convert(source);

        assertNull(result.getId());
        assertEquals(source.getEmail(), result.getEmail());
        assertEquals(source.getPassword(), result.getPassword());
        assertEquals(UserType.RESTAURANT_OWNER, result.getUserType());
        assertTrue(result.isActive());
        assertNull(result.getCreatedAt());
        assertNull(result.getUpdatedAt());
    }

    @Test
    @DisplayName("Should convert UserRequest with null email and password")
    void convert_ShouldConvertUserRequestWithNullFields() {

        UserRequest source = new UserRequest();

        User result = userRequestToDomainConverter.convert(source);

        assertNull(result.getId());
        assertNull(result.getEmail());
        assertNull(result.getPassword());
        assertEquals(UserType.RESTAURANT_OWNER, result.getUserType());
        assertTrue(result.isActive());
        assertNull(result.getCreatedAt());
        assertNull(result.getUpdatedAt());
    }

}