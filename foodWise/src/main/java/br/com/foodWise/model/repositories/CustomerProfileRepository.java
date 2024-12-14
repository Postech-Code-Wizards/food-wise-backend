package br.com.foodwise.model.repositories;

import br.com.foodwise.model.entities.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long> {
    Optional<CustomerProfile> findByUserEmail(String email);
}
