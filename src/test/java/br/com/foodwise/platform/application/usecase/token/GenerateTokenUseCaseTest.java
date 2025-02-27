package br.com.foodwise.platform.application.usecase.token;

import br.com.foodwise.platform.gateway.entities.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenerateTokenUseCaseTest {

    @InjectMocks
    private GenerateTokenUseCase useCase;

    @Mock
    private ExpirationDateUseCase expirationDateUseCase;

    private String secret = "secret";

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(useCase, "secret", secret);
    }

    @Test
    @DisplayName("Should generate valid token")
    void execute_shouldGenerateValidToken() {
        UserEntity userEntity = Instancio.create(UserEntity.class);
        userEntity.setEmail("test@example.com");
        Instant expiration = LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
        when(expirationDateUseCase.execute()).thenReturn(expiration);

        String token = useCase.execute(userEntity);

        assertNotNull(token);
        assertDoesNotThrow(() -> JWT.require(Algorithm.HMAC256(secret)).build().verify(token));
        assertEquals("test@example.com", JWT.decode(token).getSubject());
        assertTrue(expiration.isAfter(JWT.decode(token).getExpiresAt().toInstant()));
        assertEquals("auth-api", JWT.decode(token).getIssuer());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException on generate token")
    void execute_shouldThrowIllegalArgumentException_whenJWTCreationExceptionOccurs() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@example.com");
        when(expirationDateUseCase.execute()).thenThrow(new JWTCreationException("Test Exception", new Exception()));

        assertThrows(IllegalArgumentException.class, () -> useCase.execute(userEntity));
    }
}