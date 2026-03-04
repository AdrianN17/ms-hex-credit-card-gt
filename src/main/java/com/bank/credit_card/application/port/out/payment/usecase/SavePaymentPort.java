package com.bank.credit_card.application.port.out.payment.usecase;

import com.bank.credit_card.domain.payment.Payment;

import java.util.Optional;
import java.util.UUID;

public interface SavePaymentPort {
    Optional<UUID> save(Payment payment);
}
