package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.gateway.entities.UserEntity;
import br.com.foodwise.platform.gateway.entities.utils.CryptographyUtil;
import br.com.foodwise.platform.gateway.repository.UserRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.BusinessException;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.PasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class UpdatePasswordUseCase {

    private final UserRepository userRepository;

    public void execute(PasswordRequest passwordRequest) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (UserEntity) authentication.getPrincipal();

        boolean isValid = encoder.matches(passwordRequest.getPassword(), user.getPassword());
        if (isValid) {
            user.setPassword(CryptographyUtil.getEncryptedPassword(passwordRequest.getNewPassword()));
        } else {
            throw new BusinessException("INCORRECT_PASSWORD", HttpStatus.BAD_REQUEST, "");
        }

        user.setUpdatedAt(ZonedDateTime.now());
        userRepository.save(user);
    }

}
