package com.bank.credit_card.application.port.out.consumption.usecase;

import com.bank.credit_card.application.port.in.query.view.LoadConsumptionView;

import java.util.Optional;

@FunctionalInterface
public interface LoadConsumptionPort {
    Optional<LoadConsumptionView> load(Long consumptonId);
}
