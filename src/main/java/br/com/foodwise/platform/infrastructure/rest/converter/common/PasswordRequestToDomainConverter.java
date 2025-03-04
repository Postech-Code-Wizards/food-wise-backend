package br.com.foodwise.platform.infrastructure.rest.converter.common;

import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.PasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordRequestToDomainConverter {

    public User convert(PasswordRequest source) {
        return new User(
                null,
                null,
                source.getPassword(),
                null,
                false,
                null,
                null,
                null
        );
    }
}