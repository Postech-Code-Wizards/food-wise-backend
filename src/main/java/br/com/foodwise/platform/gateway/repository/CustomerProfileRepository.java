package br.com.foodwise.platform.gateway.repository;

import br.com.foodwise.platform.gateway.entities.CustomerProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerProfileRepository extends JpaRepository<CustomerProfileEntity, Long> {
    Optional<CustomerProfileEntity> findByUserEmail(String email);
}
