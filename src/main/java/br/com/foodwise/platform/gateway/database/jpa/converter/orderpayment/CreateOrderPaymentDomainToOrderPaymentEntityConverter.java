package br.com.foodwise.platform.gateway.database.jpa.converter.orderpayment;

import br.com.foodwise.platform.domain.OrderPayment;
import br.com.foodwise.platform.gateway.database.jpa.entities.OrderPaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateOrderPaymentDomainToOrderPaymentEntityConverter implements Converter<OrderPayment, OrderPaymentEntity> {

    @Override
    public OrderPaymentEntity convert(OrderPayment source) {
        return new OrderPaymentEntity(
                source.getId(),
                source.getPaymentStatus(),
                source.getTransactionReference(),
                source.getTransactionDate(),
                null,
                null,
                source.getPaymentMethod()
        );
    }

}
