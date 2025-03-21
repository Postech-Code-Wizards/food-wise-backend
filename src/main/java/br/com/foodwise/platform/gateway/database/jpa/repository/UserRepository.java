package br.com.foodwise.platform.gateway.database.jpa.repository;

import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<UserEntity> findByIdAndUserTypeAndDeletedAtIsNull(long id, UserType userType);

}
