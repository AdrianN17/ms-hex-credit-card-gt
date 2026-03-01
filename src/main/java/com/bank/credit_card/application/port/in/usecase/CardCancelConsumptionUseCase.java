package com.bank.credit_card.application.port.in.usecase;

import com.bank.credit_card.application.port.in.command.CardCancelConsumptionCommand;

@FunctionalInterface
public interface CardCancelConsumptionUseCase {
    void cancelConsumption(CardCancelConsumptionCommand cardCancelConsumptionCommand);
}
