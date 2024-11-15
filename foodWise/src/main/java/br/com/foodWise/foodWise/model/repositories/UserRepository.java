package br.com.foodWise.foodWise.model.repositories;

import br.com.foodWise.foodWise.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
