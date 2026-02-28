package com.bank.credit_card.application.port.in.card;

import java.math.BigDecimal;

@FunctionalInterface
public interface CardProcessConsumptionUseCase {
    void processConsumption(Long cardId, BigDecimal amount);
}
