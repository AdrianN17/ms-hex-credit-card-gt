package com.bank.credit_card.application.port.out.payment.usecase;

import com.bank.credit_card.domain.payment.Payment;

import java.util.Optional;

public interface SavePaymentPort {
    Optional<Long> save(Payment payment);
}
