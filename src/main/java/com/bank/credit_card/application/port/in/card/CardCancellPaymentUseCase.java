package com.bank.credit_card.application.port.in.card;

@FunctionalInterface
public interface CardCancellPaymentUseCase {
    void cancellPayment(Long identifierId, Long paymentId);
}
