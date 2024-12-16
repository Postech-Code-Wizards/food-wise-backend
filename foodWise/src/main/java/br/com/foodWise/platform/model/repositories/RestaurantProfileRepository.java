package br.com.foodwise.platform.model.repositories;

import br.com.foodwise.platform.model.entities.RestaurantProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantProfileRepository extends JpaRepository<RestaurantProfile, Long> {
    Optional<RestaurantProfile> findByUserEmail(String email);

    Optional<RestaurantProfile> findByBusinessName(String businessName);
}
