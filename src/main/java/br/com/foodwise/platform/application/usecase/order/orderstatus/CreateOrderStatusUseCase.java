package br.com.foodwise.platform.application.usecase.order.orderstatus;

import br.com.foodwise.platform.domain.OrderStatus;
import br.com.foodwise.platform.domain.enums.OrderStage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOrderStatusUseCase {

    public OrderStatus create(OrderStage orderStage) {
        return new OrderStatus(
                null,
                orderStage,
                null,
                null
        );
    }

}
