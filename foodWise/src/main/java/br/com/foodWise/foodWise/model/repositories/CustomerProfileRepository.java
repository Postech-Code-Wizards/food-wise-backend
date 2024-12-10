package br.com.foodWise.foodWise.model.repositories;

import br.com.foodWise.foodWise.model.entities.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long> {
    Optional<CustomerProfile> findByUserEmail(String email);
}
