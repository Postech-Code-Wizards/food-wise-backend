package br.com.foodwise.platform.model.repositories;

import br.com.foodwise.platform.model.entities.User;
import br.com.foodwise.platform.model.entities.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findByIdAndUserTypeAndDeletedAtIsNull(long id, UserType userType);

}
