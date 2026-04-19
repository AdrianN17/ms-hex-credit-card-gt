package com.bank.credit_card.application.port.in.usecase;

import com.bank.credit_card.application.port.in.command.CardProcessConsumptionCommand;
import com.bank.credit_card.domain.consumption.vo.ConsumptionId;

@FunctionalInterface
public interface ProcessConsumptionUseCase {
    ConsumptionId processConsumption(CardProcessConsumptionCommand cardProcessConsumptionCommand);
}
