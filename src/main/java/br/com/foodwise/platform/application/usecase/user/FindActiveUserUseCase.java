package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.gateway.entities.UserEntity;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.gateway.repository.UserRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindActiveUserUseCase {

    private final UserRepository userRepository;

    public UserEntity execute(long id, UserType userType) {
        return userRepository.findByIdAndUserTypeAndDeletedAtIsNull(id, userType)
                .orElseThrow(() -> new BusinessException("USER_DOES_NOT_EXIST", HttpStatus.NOT_FOUND, "User not found or already deleted"));
    }

}
