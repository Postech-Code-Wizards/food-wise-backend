package br.com.foodWise.foodWise.model.repositories;

import br.com.foodWise.foodWise.model.entities.RestaurantProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantProfileRepository extends JpaRepository<RestaurantProfile, Long> {
}
