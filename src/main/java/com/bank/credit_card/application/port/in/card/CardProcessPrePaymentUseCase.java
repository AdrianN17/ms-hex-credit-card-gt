package com.bank.credit_card.application.port.in.card;

import com.bank.credit_card.domain.benefit.Point;
import com.bank.credit_card.domain.payment.Payment;

@FunctionalInterface
public interface CardProcessPrePaymentUseCase {
    void processPrePayment(Payment pago, Point point);
}
