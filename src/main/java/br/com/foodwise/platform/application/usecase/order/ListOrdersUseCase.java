package br.com.foodwise.platform.application.usecase.order;

import br.com.foodwise.platform.domain.Order;
import br.com.foodwise.platform.gateway.OrderGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListOrdersUseCase {
    private final OrderGateway orderGateway;

    public List<Order> listOrders() {
        return orderGateway.retrieveAllOrders();
    }

}
