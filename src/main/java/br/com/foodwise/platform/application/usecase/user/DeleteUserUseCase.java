package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.gateway.UserGateway;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserUseCase {

    private final ValidateUserIsAuthenticatedUseCase validateUserIsAuthenticatedUseCase;
    private final UserGateway userGateway;

    public void execute(long id, UserType userType) {
        validateUserIsAuthenticatedUseCase.execute(id);

        var user = userGateway.findByIdAndUserTypeAndDeletedAtIsNull(id, userType)
                .orElseThrow(() -> new ResourceNotFoundException("USER_DOES_NOT_EXIST", ""));
        user.delete();
        userGateway.save(user);
    }

}
