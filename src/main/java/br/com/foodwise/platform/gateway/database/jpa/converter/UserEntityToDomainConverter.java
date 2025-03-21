package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEntityToDomainConverter {

    public User convert(UserEntity source) {
        return new User(
                source.getId(),
                source.getEmail(),
                source.getPassword(),
                source.getUserType(),
                source.isActive(),
                source.getCreatedAt(),
                source.getUpdatedAt(),
                source.getDeletedAt()
        );
    }
}
