package br.com.foodwise.platform.infrastructure.rest.converter.common;

import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRequestToDomainConverter {

    public User convert(UserRequest source) {
        return new User(
                null,
                source.getEmail(),
                source.getPassword(),
                UserType.RESTAURANT_OWNER,
                true,
                null,
                null,
                null
        );
    }
}