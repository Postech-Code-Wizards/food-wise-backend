package br.com.foodwise.platform.gateway;

import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.domain.enums.UserType;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserGateway {

    UserDetails findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findByIdAndUserTypeAndDeletedAtIsNull(long id, UserType userType);

    void save(User user);

    void delete(long id, UserType userType);
}
