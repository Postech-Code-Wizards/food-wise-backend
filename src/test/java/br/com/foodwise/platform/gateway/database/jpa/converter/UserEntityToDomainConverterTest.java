package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserEntityToDomainConverterTest {

    @InjectMocks
    private UserEntityToDomainConverter userEntityToDomainConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test conversion of UserEntity to User")
    void testConvertUserEntityToDomain() {

        UserEntity source = Instancio.create(UserEntity.class);

        User domain = userEntityToDomainConverter.convert(source);

        assertNotNull(domain);
        assertEquals(source.getId(), domain.getId());
        assertEquals(source.getEmail(), domain.getEmail());
        assertEquals(source.getPassword(), domain.getPassword());
        assertEquals(source.isActive(), domain.isActive());
        assertEquals(source.getCreatedAt(), domain.getCreatedAt());
        assertEquals(source.getUpdatedAt(), domain.getUpdatedAt());
        assertEquals(source.getDeletedAt(), domain.getDeletedAt());
    }

}