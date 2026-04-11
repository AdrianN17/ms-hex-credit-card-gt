package com.bank.credit_card.application.port.in.usecase;

import com.bank.credit_card.application.port.in.command.CardCancelConsumptionCommand;

import java.util.UUID;

@FunctionalInterface
public interface CardCancelConsumptionUseCase {
    UUID cancelConsumption(CardCancelConsumptionCommand cardCancelConsumptionCommand);
}
