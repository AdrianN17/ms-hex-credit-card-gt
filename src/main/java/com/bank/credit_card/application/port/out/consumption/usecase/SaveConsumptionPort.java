package com.bank.credit_card.application.port.out.consumption.usecase;

import com.bank.credit_card.domain.consumption.Consumption;

import java.util.Optional;

@FunctionalInterface
public interface SaveConsumptionPort {
    Optional<Long> save(Consumption consumption);
}
