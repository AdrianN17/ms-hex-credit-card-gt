package com.bank.credit_card.application.port.out.consumption.usecase;

import com.bank.credit_card.domain.consumption.Consumption;

import java.util.Optional;
import java.util.UUID;

@FunctionalInterface
public interface SaveConsumptionPort {
    Optional<UUID> save(Consumption consumption);
}
