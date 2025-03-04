package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.gateway.UserGateway;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class UpdateUserEmailUseCase {

    private final UserGateway userGateway;

    @Transactional
    public void execute(User user, Long id, UserType userType) {
        var existingUser = userGateway.findByIdAndUserTypeAndDeletedAtIsNull(id, userType)
                .orElseThrow(() -> new ResourceNotFoundException("USER_DOES_NOT_EXIST", ""));

        if (ObjectUtils.isNotEmpty(user.getEmail()) && !user.getPassword().equals(existingUser.getPassword())) {
            User userSave = populate(existingUser, user);
            userGateway.save(userSave);
        }
    }

    private static User populate(User existingUser, User user) {
        return new User(
                existingUser.getId(),
                user.getEmail(),
                existingUser.getPassword(),
                existingUser.getUserType(),
                existingUser.isActive(),
                existingUser.getCreatedAt(),
                ZonedDateTime.now(),
                existingUser.getDeletedAt()
        );
    }

}
