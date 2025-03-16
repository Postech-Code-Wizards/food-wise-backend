package br.com.foodwise.platform.gateway;

import br.com.foodwise.platform.domain.OrderPayment;

public interface OrderPaymentGateway {
    OrderPayment save(OrderPayment orderPayment);

    OrderPayment findOrderPaymentById(Long id);
}
