package com.bank.credit_card.application.port.in.card;

@FunctionalInterface
public interface CardCancelConsumptionUseCase {
    void cancelConsumption(Long identifierId, Long consumptionId);
}
