package br.com.foodwise.platform.gateway.database.jpa.repository;

import br.com.foodwise.platform.gateway.database.jpa.entities.RestaurantOwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantOwnerRepository extends JpaRepository<RestaurantOwnerEntity, Long> {
}
