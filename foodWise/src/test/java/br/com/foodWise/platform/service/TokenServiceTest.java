package br.com.foodwise.platform.service;

import br.com.foodwise.platform.model.entities.User;
import br.com.foodwise.platform.service.TokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class TokenServiceTest {

    private static final String SECRET = "mySecretKey";
    private static final String ISSUER = "auth-api";

    @InjectMocks
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        tokenService = new TokenService(SECRET);
    }

    @Test
    void generateToken_shouldReturnValidToken() {
        var user = new User();
        user.setEmail("test@code-wizards.com");

        var token = tokenService.generateToken(user);

        assertNotNull(token);
        assertFalse(token.isEmpty());
        var subject = JWT.require(Algorithm.HMAC256(SECRET))
                .withIssuer(ISSUER)
                .build()
                .verify(token)
                .getSubject();
        assertEquals(user.getEmail(), subject);
    }

    @Test
    void validateToken_shouldReturnSubjectWhenTokenIsValid() {
        var user = new User();
        user.setEmail("test@code-wizards.com");
        var validToken = tokenService.generateToken(user);

        var subject = tokenService.validateToken(validToken);

        assertEquals(user.getEmail(), subject);
    }

    @Test
    void validateToken_shouldReturnEmptyStringWhenTokenIsInvalid() {
        var invalidToken = "invalid.token.here";

        var subject = tokenService.validateToken(invalidToken);

        assertTrue(subject.isEmpty());
    }

    @Test
    void validateToken_shouldReturnEmptyStringWhenTokenIsExpired() {
        var user = new User();
        user.setEmail("test@code-wizards.com");

        var algorithm = Algorithm.HMAC256(SECRET);
        var expiredToken = JWT.create()
                .withIssuer(ISSUER)
                .withSubject(user.getEmail())
                .withExpiresAt(Instant.now().minusSeconds(3600))
                .sign(algorithm);

        var subject = tokenService.validateToken(expiredToken);
        assertTrue(subject.isEmpty());
    }
}