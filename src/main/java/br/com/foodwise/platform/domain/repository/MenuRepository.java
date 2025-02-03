package br.com.foodwise.platform.domain.repository;

import br.com.foodwise.platform.domain.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findTop1ByRestaurantProfileBusinessName(String businessName);
}