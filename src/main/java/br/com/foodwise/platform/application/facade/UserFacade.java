package br.com.foodwise.platform.application.facade;

import br.com.foodwise.platform.application.usecase.user.*;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.application.facade.converter.common.PasswordRequestToDomainConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.PasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateUserEmailUseCase updateUserEmailUseCase;
    private final UpdatePasswordUseCase updatePasswordUseCase;
    private final LoadUserByUsernameUseCase loadUserByUsernameUseCase;
    private final PasswordRequestToDomainConverter passwordRequestToDomainConverter;

    public void updateUserEmail(User user, Long id, UserType userType) {
        updateUserEmailUseCase.execute(user, id, userType);
    }

    public void delete(long id, UserType userType) {
        deleteUserUseCase.execute(id, userType);
    }

    public void updatePassword(PasswordRequest passwordRequest) {
        User user = passwordRequestToDomainConverter.convert(passwordRequest);
        updatePasswordUseCase.execute(user, passwordRequest.getNewPassword());
    }

    public UserDetails loadUserByUsername(String email) {
        return loadUserByUsernameUseCase.loadUserByUsername(email);
    }

}
