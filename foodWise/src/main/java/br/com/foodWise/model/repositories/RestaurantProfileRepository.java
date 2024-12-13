package br.com.foodWise.model.repositories;

import br.com.foodWise.model.entities.RestaurantProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantProfileRepository extends JpaRepository<RestaurantProfile, Long> {
    Optional<RestaurantProfile> findByUserEmail(String email);

    Optional<RestaurantProfile> findByBusinessName(String businessName);
}
