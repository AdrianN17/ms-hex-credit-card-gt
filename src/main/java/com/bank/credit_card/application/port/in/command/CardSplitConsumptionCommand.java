package com.bank.credit_card.application.port.in.command;

public record CardSplitConsumptionCommand(
        Long consumptionId,
        Integer installments) {
}
