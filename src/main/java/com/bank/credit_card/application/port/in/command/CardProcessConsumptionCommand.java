package com.bank.credit_card.application.port.in.command;

import com.bank.credit_card.domain.base.enums.CurrencyEnum;

import java.math.BigDecimal;

public record CardProcessConsumptionCommand(
        BigDecimal amount,
        CurrencyEnum currency,
        Long cardId,
        String sellerName) {
}
