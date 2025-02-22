package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.domain.entities.User;
import br.com.foodwise.platform.domain.entities.enums.UserType;
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
        User userFound = findActiveUser.execute(id, userType);
        deleteUser(userFound);
    }

    private static void deleteUser(User userFound) {
        userFound.setActive(false);
        userFound.setDeletedAt(ZonedDateTime.now());
    }

}
