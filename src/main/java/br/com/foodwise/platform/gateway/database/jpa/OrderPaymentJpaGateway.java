package br.com.foodwise.platform.gateway.database.jpa;

import br.com.foodwise.platform.domain.OrderPayment;
import br.com.foodwise.platform.gateway.OrderPaymentGateway;
import br.com.foodwise.platform.gateway.database.jpa.converter.orderpayment.CreateOrderPaymentDomainToOrderPaymentEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.orderpayment.OrderPaymentEntityToOrderPaymentConverter;
import br.com.foodwise.platform.gateway.database.jpa.repository.OrderPaymentRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.order.OrderPaymentEmptyException;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.order.OrderStatusNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderPaymentJpaGateway implements OrderPaymentGateway {
    private final OrderPaymentRepository orderPaymentRepository;
    private final CreateOrderPaymentDomainToOrderPaymentEntityConverter toOrderPaymentEntityConverter;
    private final OrderPaymentEntityToOrderPaymentConverter toOrderPaymentConverter;

    @Override
    public OrderPayment save(OrderPayment orderPayment) {
        var orderPaymentEntity = toOrderPaymentEntityConverter.convert(orderPayment);
        if (ObjectUtils.isEmpty(orderPaymentEntity)) {
            throw new OrderPaymentEmptyException("Order payment is empty");
        }
        var orderPaymentSaved = orderPaymentRepository.save(orderPaymentEntity);
        return toOrderPaymentConverter.convert(orderPaymentSaved);
    }

    @Override
    public OrderPayment findOrderPaymentById(Long id) {
        return toOrderPaymentConverter.convert(orderPaymentRepository.findById(id)
                .orElseThrow(() -> new OrderStatusNotFoundException("Order Payment not found for id " + id)));
    }
}
