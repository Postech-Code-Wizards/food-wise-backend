package br.com.foodwise.platform.gateway.database.jpa;

import br.com.foodwise.platform.domain.OrderStatus;
import br.com.foodwise.platform.gateway.OrderStatusGateway;
import br.com.foodwise.platform.gateway.database.jpa.converter.orderstatus.OrderStatusEntityToOrderStatusDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.orderstatus.CreateOrderStatusToOrderStatusEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.repository.OrderStatusRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.order.OrderStatusEmptyException;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.order.OrderStatusNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderStatusJpaGateway implements OrderStatusGateway {
    private final OrderStatusRepository orderStatusRepository;
    private final OrderStatusEntityToOrderStatusDomainConverter toOrderStatusConverter;
    private final CreateOrderStatusToOrderStatusEntityConverter toOrderStatusEntityConverter;

    @Override
    public OrderStatus save(OrderStatus orderStatus) {
        var orderStatusEntity = toOrderStatusEntityConverter.convert(orderStatus);
        if (ObjectUtils.isEmpty(orderStatusEntity)) {
            throw new OrderStatusEmptyException("Order status is empty");
        }
        var orderSaved = orderStatusRepository.save(orderStatusEntity);
        return toOrderStatusConverter.convert(orderSaved);
    }

    @Override
    public OrderStatus findOrderStatusById(Long id) {
        return toOrderStatusConverter.convert(orderStatusRepository.findById(id)
                .orElseThrow(() -> new OrderStatusNotFoundException("Order Status not found for id " + id)));
    }
}
