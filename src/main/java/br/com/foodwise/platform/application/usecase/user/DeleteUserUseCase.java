package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.gateway.entities.UserEntity;
import br.com.foodwise.platform.domain.enums.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class DeleteUserUseCase {

    private final ValidateUserIsAuthenticatedUseCase validateUserIsAuthenticated;
    private final FindActiveUserUseCase findActiveUser;

    public void execute(long id, UserType userType) {
        validateUserIsAuthenticated.execute(id);
        UserEntity userEntityFound = findActiveUser.execute(id, userType);
        deleteUser(userEntityFound);
    }

    private static void deleteUser(UserEntity userEntityFound) {
        userEntityFound.setActive(false);
        userEntityFound.setDeletedAt(ZonedDateTime.now());
    }

}
