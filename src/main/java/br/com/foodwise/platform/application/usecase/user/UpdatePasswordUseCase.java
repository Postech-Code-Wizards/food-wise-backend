package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.gateway.UserGateway;
import br.com.foodwise.platform.gateway.database.jpa.converter.UserEntityToDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import br.com.foodwise.platform.gateway.database.jpa.entities.utils.CryptographyUtil;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class UpdatePasswordUseCase {

    private final UserGateway userGateway;
    private final UserEntityToDomainConverter userEntityToDomainConverter;

    public void execute(User user, String newPassword) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userEntityAuthentication = (UserEntity) authentication.getPrincipal();
        var userAuthentication = userEntityToDomainConverter.convert(userEntityAuthentication);

        boolean isValid = encoder.matches(user.getPassword(), userEntityAuthentication.getPassword());
        if (isValid) {
            var userSave = populate(userAuthentication, newPassword);
            userGateway.save(userSave);
        } else {
            throw new BusinessException("INCORRECT_PASSWORD", HttpStatus.BAD_REQUEST, "");
        }
    }

    private static User populate(User userAuthentication, String newPassword) {
        return new User(
                userAuthentication.getId(),
                userAuthentication.getEmail(),
                CryptographyUtil.getEncryptedPassword(newPassword),
                userAuthentication.getUserType(),
                userAuthentication.isActive(),
                userAuthentication.getCreatedAt(),
                ZonedDateTime.now(),
                userAuthentication.getDeletedAt()
        );
    }

}
