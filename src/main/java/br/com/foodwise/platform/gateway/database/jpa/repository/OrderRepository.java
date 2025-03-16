package br.com.foodwise.platform.gateway.database.jpa.repository;

import br.com.foodwise.platform.gateway.database.jpa.entities.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

}
