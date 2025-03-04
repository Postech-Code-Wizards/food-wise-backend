package br.com.foodwise.platform.application.service;

import br.com.foodwise.platform.application.usecase.user.*;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.domain.enums.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateUserEmailUseCase updateUserEmailUseCase;
    private final UpdatePasswordUseCase updatePasswordUseCase;
    private final LoadUserByUsernameUseCase loadUserByUsernameUseCase;

    public void updateUserEmail(User user, Long id, UserType userType) {
        updateUserEmailUseCase.execute(user, id, userType);
    }

    public void delete(long id, UserType userType) {
        deleteUserUseCase.execute(id, userType);
    }

    public void updatePassword(User user, String newPassword) {
        updatePasswordUseCase.execute(user, newPassword);
    }

    public UserDetails loadUserByUsername(String email) {
        return loadUserByUsernameUseCase.loadUserByUsername(email);
    }

}
