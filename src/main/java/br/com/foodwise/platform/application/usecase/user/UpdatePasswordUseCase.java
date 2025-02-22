package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.domain.entities.User;
import br.com.foodwise.platform.domain.entities.utils.CryptographyUtil;
import br.com.foodwise.platform.domain.repository.UserRepository;
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
        var user = (User) authentication.getPrincipal();

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
