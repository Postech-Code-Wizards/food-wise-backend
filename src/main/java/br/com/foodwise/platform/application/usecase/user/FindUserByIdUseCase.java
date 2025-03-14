package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindUserByIdUseCase {

    private final UserGateway userGateway;

    public User findUserById(Long id) {
        return userGateway.findUserById(id);
    }
}
