package br.com.foodwise.platform.application.facade;

import br.com.foodwise.platform.application.usecase.token.GenerateTokenUseCase;
import br.com.foodwise.platform.application.usecase.token.ValidateTokenUseCase;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenFacade {

    private final GenerateTokenUseCase generateTokenUseCase;
    private final ValidateTokenUseCase validateTokenUseCase;

    public String generateToken(UserEntity userEntity) {
        return generateTokenUseCase.execute(userEntity);
    }

    public String validateToken(String token) {
        return validateTokenUseCase.execute(token);
    }

}
