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

class UserDomainToEntityConverterTest {

    @InjectMocks
    private UserDomainToEntityConverter userDomainToEntityConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test conversion of User to UserEntity")
    void testConvertUserToEntity() {

        User source = Instancio.create(User.class);

        UserEntity entity = userDomainToEntityConverter.convert(source);

        assertNotNull(entity);
        assertEquals(source.getId(), entity.getId());
        assertEquals(source.getPassword(), entity.getPassword());
        assertEquals(source.getEmail(), entity.getEmail());
        assertEquals(source.getCreatedAt(), entity.getCreatedAt());
        assertEquals(source.getUpdatedAt(), entity.getUpdatedAt());

    }

}