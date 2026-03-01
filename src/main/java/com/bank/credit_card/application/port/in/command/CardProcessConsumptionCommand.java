package com.bank.credit_card.application.port.in.command;

import com.bank.credit_card.domain.base.CurrencyEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CardProcessConsumptionCommand(
        BigDecimal amount,
        CurrencyEnum currency,
        LocalDateTime consumptionDate,
        LocalDateTime consumptionApprobationDate,
        Long cardId,
        String sellerName) {
}
