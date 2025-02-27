package br.com.foodwise.platform.gateway.repository;

import br.com.foodwise.platform.gateway.entities.UserEntity;
import br.com.foodwise.platform.domain.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserDetails findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<UserEntity> findByIdAndUserTypeAndDeletedAtIsNull(long id, UserType userType);

}
