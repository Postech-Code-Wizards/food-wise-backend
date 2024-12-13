package br.com.foodwise.foodwise.model.repositories;

import br.com.foodwise.foodwise.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
