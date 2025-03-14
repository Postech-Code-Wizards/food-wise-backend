package br.com.foodwise.platform.application.facade;

import br.com.foodwise.platform.application.usecase.token.ExpirationDateUseCase;
import br.com.foodwise.platform.application.usecase.token.GenerateTokenUseCase;
import br.com.foodwise.platform.application.usecase.token.ValidateTokenUseCase;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TokenFacadeTest {

    @Mock
    private GenerateTokenUseCase generateTokenUseCase;

    @Mock
    private ValidateTokenUseCase validateTokenUseCase;

    @Mock
    private ExpirationDateUseCase expirationDateUseCase;

    @InjectMocks
    private TokenFacade tokenFacade;

    private UserEntity userEntity;
    private String token;

    @BeforeEach
    void setUp() {
        userEntity = Instancio.create(UserEntity.class);
        token = "testToken";
    }

    @Test
    void generateToken_ShouldCallGenerateTokenUseCase() {
        when(generateTokenUseCase.execute(userEntity)).thenReturn(token);

        String generatedToken = tokenFacade.generateToken(userEntity);

        assertEquals(token, generatedToken);
        verify(generateTokenUseCase, times(1)).execute(userEntity);
    }

    @Test
    void validateToken_ShouldCallValidateTokenUseCase() {
        when(validateTokenUseCase.execute(token)).thenReturn("test@example.com");

        String validatedEmail = tokenFacade.validateToken(token);

        assertEquals("test@example.com", validatedEmail);
        verify(validateTokenUseCase, times(1)).execute(token);
    }
}