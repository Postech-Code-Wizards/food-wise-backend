package br.com.foodwise.platform.gateway.database.jpa.repository;

import br.com.foodwise.platform.gateway.database.jpa.entities.RestaurantProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantProfileRepository extends JpaRepository<RestaurantProfileEntity, Long> {
    Optional<RestaurantProfileEntity> findByUserEntityEmail(String email);

    Optional<RestaurantProfileEntity> findByBusinessName(String businessName);
}
