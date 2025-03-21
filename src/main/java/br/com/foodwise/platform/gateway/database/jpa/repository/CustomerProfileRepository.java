package br.com.foodwise.platform.gateway.database.jpa.repository;

import br.com.foodwise.platform.gateway.database.jpa.entities.CustomerProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerProfileRepository extends JpaRepository<CustomerProfileEntity, Long> {
    Optional<CustomerProfileEntity> findByUserEntityEmail(String email);
}
