package br.com.foodwise.platform.gateway.database.jpa.converter.orderpayment;

import br.com.foodwise.platform.domain.OrderPayment;
import br.com.foodwise.platform.gateway.database.jpa.entities.OrderPaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderPaymentEntityToOrderPaymentConverter implements Converter<OrderPaymentEntity, OrderPayment> {

    @Override
    public OrderPayment convert(OrderPaymentEntity source) {
        return new OrderPayment(
                source.getId(),
                source.getPaymentStatus(),
                source.getTransactionReference(),
                source.getTransactionDate(),
                source.getCreatedAt(),
                source.getUpdatedAt(),
                source.getPaymentMethod()
        );
    }
}
