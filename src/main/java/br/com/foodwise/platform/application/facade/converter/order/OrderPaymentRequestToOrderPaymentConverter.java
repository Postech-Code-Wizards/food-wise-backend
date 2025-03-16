package br.com.foodwise.platform.application.facade.converter.order;

import br.com.foodwise.platform.domain.OrderPayment;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.order.OrderPaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderPaymentRequestToOrderPaymentConverter implements Converter<OrderPaymentRequest, OrderPayment> {
    @Override
    public OrderPayment convert(OrderPaymentRequest source) {
        return new OrderPayment(
                null,
                null,
                null,
                null,
                null,
                null,
                source.getPaymentMethod()
        );
    }
}
