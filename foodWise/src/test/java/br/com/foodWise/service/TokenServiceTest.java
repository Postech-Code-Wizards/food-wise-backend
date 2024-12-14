package br.com.foodwise.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(properties = "api.security.token.secret=mock-secret-key")
class TokenServiceTest {
    @InjectMocks
    private TokenService tokenService;

    @Value("${api.security.token.secret}")
    private String secret;

   /* @Test
    void shouldGenerateTokenSuccessfully() {
        var user = Mockito.mock(User.class);
        when(user.getEmail()).thenReturn("user@example.com");

        var token = tokenService.generateToken(user);

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void shouldValidateTokenSuccessfully() {
        var user = Mockito.mock(User.class);
        when(user.getEmail()).thenReturn("user@example.com");

        var token = tokenService.generateToken(user);

        var validatedEmail = tokenService.validateToken(token);

        assertEquals("user@example.com", validatedEmail);
    }

    @Test
    void shouldReturnEmptyForInvalidToken() {
        var invalidToken = "invalidToken";

        var validatedEmail = tokenService.validateToken(invalidToken);

        assertEquals("", validatedEmail);
    }

    @Test
    void shouldReturnEmptyForTamperedToken() {
        var user = Mockito.mock(User.class);
        when(user.getEmail()).thenReturn("user@example.com");

        var token = tokenService.generateToken(user);

        var tamperedToken = token.substring(0, token.length() - 1) + "x";

        var validatedEmail = tokenService.validateToken(tamperedToken);

        assertEquals("", validatedEmail);
    }

    @Test
    void shouldThrowExceptionWhenGeneratingTokenFails() {
        var user = Mockito.mock(User.class);

        when(user.getEmail()).thenThrow(new RuntimeException("Email unavailable"));

        var exception = assertThrows(RuntimeException.class, () -> {
            tokenService.generateToken(user);
        });

        assertTrue(exception.getMessage().contains("Error while generating token"));
    }*/
}