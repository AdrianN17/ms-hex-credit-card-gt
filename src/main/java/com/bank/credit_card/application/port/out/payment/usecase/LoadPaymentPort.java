package com.bank.credit_card.application.port.out.payment.usecase;

import com.bank.credit_card.application.port.in.query.view.LoadPaymentView;

import java.util.Optional;

@FunctionalInterface
public interface LoadPaymentPort {
    Optional<LoadPaymentView> load(Long paymentId);
}
