package com.bank.credit_card.application.port.in.usecase;

import com.bank.credit_card.application.port.in.command.CardSplitConsumptionCommand;
import com.bank.credit_card.domain.consumption.Consumption;

import java.util.List;

@FunctionalInterface
public interface CardSplitConsumptionUseCase {
    List<Consumption> splitConsumption(CardSplitConsumptionCommand cardSplitConsumptionCommand);
}
