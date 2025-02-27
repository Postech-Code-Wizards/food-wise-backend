package br.com.foodwise.platform.gateway.repository;

import br.com.foodwise.platform.gateway.entities.RestaurantProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantProfileRepository extends JpaRepository<RestaurantProfileEntity, Long> {
    Optional<RestaurantProfileEntity> findByUserEmail(String email);

    Optional<RestaurantProfileEntity> findByBusinessName(String businessName);
}
