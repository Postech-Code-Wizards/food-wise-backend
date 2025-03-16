package br.com.foodwise.platform.gateway.database.jpa.repository;

import br.com.foodwise.platform.gateway.database.jpa.entities.OrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatusEntity, Long> {
}
