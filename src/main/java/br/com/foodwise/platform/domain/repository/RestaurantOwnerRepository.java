package br.com.foodwise.platform.domain.repository;

import br.com.foodwise.platform.domain.entities.RestaurantOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantOwnerRepository extends JpaRepository<RestaurantOwner, Long> {
}
