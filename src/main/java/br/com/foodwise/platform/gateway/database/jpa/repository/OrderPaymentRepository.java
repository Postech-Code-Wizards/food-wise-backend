package br.com.foodwise.platform.gateway.database.jpa.repository;

import br.com.foodwise.platform.gateway.database.jpa.entities.OrderPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPaymentRepository extends JpaRepository<OrderPaymentEntity, Long> {
}
