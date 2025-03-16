package br.com.foodwise.platform.application.usecase.order;

import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.gateway.OrderGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveOrderUseCase {
    private final OrderGateway orderGateway;

    public Order retrieveOrder(Long id) {
        return orderGateway.findById(id);
    }
}
