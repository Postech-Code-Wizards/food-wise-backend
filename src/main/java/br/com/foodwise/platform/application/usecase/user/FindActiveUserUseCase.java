package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.domain.entities.User;
import br.com.foodwise.platform.domain.entities.enums.UserType;
import br.com.foodwise.platform.domain.repository.UserRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindActiveUserUseCase {

    private final UserRepository userRepository;

    public User execute(long id, UserType userType) {
        return userRepository.findByIdAndUserTypeAndDeletedAtIsNull(id, userType)
                .orElseThrow(() -> new BusinessException("USER_DOES_NOT_EXIST", HttpStatus.NOT_FOUND, "User not found or already deleted"));
    }

}
