package br.com.foodwise.platform.gateway.database.jpa;

import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.gateway.UserGateway;
import br.com.foodwise.platform.gateway.database.jpa.converter.UserDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.UserEntityToDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import br.com.foodwise.platform.gateway.database.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserJpaGateway implements UserGateway {

    private final UserRepository userRepository;
    private final UserEntityToDomainConverter userEntityToDomainConverter;
    private final UserDomainToEntityConverter userDomainToEntityConverter;

    @Override
    public UserDetails findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findByIdAndUserTypeAndDeletedAtIsNull(long id, UserType userType) {
        Optional<UserEntity> userEntityOp = userRepository.findByIdAndUserTypeAndDeletedAtIsNull(id, userType);
        if(userEntityOp.isEmpty()) {
            registerLogUserNotFound(id, userType);
            return Optional.empty();
        }

        UserEntity userEntity = userEntityOp.get();
        User user = userEntityToDomainConverter.convert(userEntity);

        return Optional.of(user);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userDomainToEntityConverter.convert(user);
        UserEntity userEntitySaved = userRepository.save(userEntity);
        return userEntityToDomainConverter.convert(userEntitySaved);
    }

    @Override
    public void delete(long id, UserType userType) {
        Optional<UserEntity> userEntityOp = userRepository.findByIdAndUserTypeAndDeletedAtIsNull(id, userType);

        if(userEntityOp.isEmpty()) {
            registerLogUserNotFound(id, userType);
        } else {
            UserEntity userEntity = userEntityOp.get();
            userEntity.setActive(false);
            userEntity.setDeletedAt(ZonedDateTime.now());
            userRepository.save(userEntity);
        }
    }

    private static void registerLogUserNotFound(long id, UserType userType) {
        log.info("User not found: id={}, userType={}", id, userType);
    }

}
