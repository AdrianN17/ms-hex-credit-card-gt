package com.bank.credit_card.application.port.out.benefit;

import com.bank.credit_card.domain.benefit.Benefit;

import java.util.Optional;

@FunctionalInterface
public interface SaveBenefitPort {
    Optional<Long> save(Benefit benefit);
}
