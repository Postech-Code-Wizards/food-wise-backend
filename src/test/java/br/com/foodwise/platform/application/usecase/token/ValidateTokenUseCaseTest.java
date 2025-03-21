package br.com.foodwise.platform.application.usecase.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ValidateTokenUseCaseTest {

    private static final String SECRET = "secret";
    @InjectMocks
    private ValidateTokenUseCase useCase;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(useCase, "secret", SECRET);
    }

    @Test
    @DisplayName("Should return subject when token is valid")
    void execute_shouldReturnSubject_whenTokenIsValid() {
        String subject = Instancio.create(String.class);
        String token = generateValidToken(subject);

        String result = useCase.execute(token);

        assertEquals(subject, result);
    }

    @Test
    @DisplayName("Should return empty string when token is invalid")
    void execute_shouldReturnEmptyString_whenTokenIsInvalid() {
        String invalidToken = "invalidToken";

        String result = useCase.execute(invalidToken);

        assertEquals("", result);
    }

    @Test
    @DisplayName("Should return empty string when token has wrong secret")
    void execute_shouldReturnEmptyString_whenTokenHasWrongSecret() {
        String subject = Instancio.create(String.class);
        String token = generateValidToken(subject);

        ReflectionTestUtils.setField(useCase, "secret", Instancio.create(String.class));

        String result = useCase.execute(token);

        assertEquals("", result);
    }

    private String generateValidToken(String subject) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withIssuer("auth-api")
                .withSubject(subject)
                .sign(algorithm);
    }
}