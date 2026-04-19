package com.bank.credit_card.application.port.in.usecase;

import com.bank.credit_card.application.port.in.command.CardCancelConsumptionCommand;
import com.bank.credit_card.domain.consumption.vo.ConsumptionId;

@FunctionalInterface
public interface CancelConsumptionUseCase {
    ConsumptionId cancelConsumption(CardCancelConsumptionCommand cardCancelConsumptionCommand);
}
