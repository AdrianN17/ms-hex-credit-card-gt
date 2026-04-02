package com.bank.credit_card.application.port.out.payment.usecase;

import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.payment.Payment;

import java.util.Optional;
import java.util.UUID;

@FunctionalInterface
public interface LoadPaymentPort {
    Optional<Payment> load(UUID paymentId, String cardId, Currency currency);
}
