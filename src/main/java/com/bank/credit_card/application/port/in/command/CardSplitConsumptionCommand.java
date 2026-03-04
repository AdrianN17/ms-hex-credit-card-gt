package com.bank.credit_card.application.port.in.command;

import java.util.UUID;

public record CardSplitConsumptionCommand(
        UUID consumptionId,
        Integer installments,
        Long cardId) {
}
