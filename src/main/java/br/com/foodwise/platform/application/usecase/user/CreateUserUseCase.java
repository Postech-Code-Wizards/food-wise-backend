package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.gateway.entities.UserEntity;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.gateway.entities.utils.CryptographyUtil;
import br.com.foodwise.platform.gateway.repository.UserRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserRepository userRepository;

    public UserEntity execute(String email, String password, UserType role) {
        if (userRepository.existsByEmail(email)) {
            throw new BusinessException("EMAIL_ALREADY_EXISTS", HttpStatus.CONFLICT, "");
        }

        var newUser = UserEntity
                .builder()
                .email(email)
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .isActive(true)
                .userType(role)
                .password(CryptographyUtil.getEncryptedPassword(password))
                .deletedAt(null)
                .build();

        this.userRepository.save(newUser);
        return newUser;
    }

}
