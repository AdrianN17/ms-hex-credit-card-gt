package com.bank.credit_card.application.port.out.consumption.usecase;

import com.bank.credit_card.domain.consumption.Consumption;

import java.util.Optional;
import java.util.UUID;

@FunctionalInterface
public interface LoadConsumptionPort {
    Optional<Consumption> load(UUID consumptonId);
}
