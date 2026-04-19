package com.bank.credit_card.application.port.in.usecase;

import com.bank.credit_card.application.port.in.command.CardSplitConsumptionCommand;
import com.bank.credit_card.domain.consumption.vo.ConsumptionId;

import java.util.List;

@FunctionalInterface
public interface SplitConsumptionUseCase {
    List<ConsumptionId> splitConsumption(CardSplitConsumptionCommand cardSplitConsumptionCommand);
}
