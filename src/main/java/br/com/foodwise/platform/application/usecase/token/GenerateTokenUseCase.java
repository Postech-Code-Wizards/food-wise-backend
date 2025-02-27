package br.com.foodwise.platform.application.usecase.token;

import br.com.foodwise.platform.gateway.entities.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenerateTokenUseCase {

    @Value("${api.security.token.secret}")
    private String secret;

    private final ExpirationDateUseCase expirationDateUseCase;

    public String execute(UserEntity userEntity) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(userEntity.getEmail())
                    .withExpiresAt(expirationDateUseCase.execute())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new IllegalArgumentException("Error while generating token", exception);
        }
    }

}
