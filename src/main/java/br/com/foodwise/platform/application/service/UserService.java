package br.com.foodwise.platform.application.service;

import br.com.foodwise.platform.application.usecase.user.*;
import br.com.foodwise.platform.domain.entities.User;
import br.com.foodwise.platform.domain.entities.enums.UserType;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.PasswordRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CreateUserUseCase createUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateUserEmailUseCase updateUserEmailUseCase;
    private final UpdatePasswordUseCase updatePasswordUseCase;
    private final LoadUserByUsernameUseCase loadUserByUsernameUseCase;

    public User createUser(String email, String password, UserType role) {
        return createUserUseCase.execute(email, password, role);
    }

    public void updateUserEmail(UserRequest userRequest, Long id, UserType userType) {
        updateUserEmailUseCase.execute(userRequest, id, userType);
    }

    public void delete(long id, UserType userType) {
        deleteUserUseCase.execute(id, userType);
    }

    public void updatePassword(PasswordRequest passwordRequest) {
        updatePasswordUseCase.execute(passwordRequest);
    }

    public UserDetails loadUserByUsername(String email) {
        return loadUserByUsernameUseCase.loadUserByUsername(email);
    }

}
