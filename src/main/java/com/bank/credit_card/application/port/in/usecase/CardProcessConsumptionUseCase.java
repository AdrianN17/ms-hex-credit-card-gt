package com.bank.credit_card.application.port.in.usecase;

import com.bank.credit_card.application.port.in.command.CardProcessConsumptionCommand;
import com.bank.credit_card.domain.consumption.Consumption;

@FunctionalInterface
public interface CardProcessConsumptionUseCase {
    Consumption processConsumption(CardProcessConsumptionCommand cardProcessConsumptionCommand);
}
