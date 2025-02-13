package br.com.foodwise.platform.domain.repository;

import br.com.foodwise.platform.domain.entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findMenuItemByName(String itemName);
}
