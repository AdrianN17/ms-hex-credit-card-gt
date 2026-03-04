package com.bank.credit_card.application.port.in.query.view;

import com.bank.credit_card.domain.base.CurrencyEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record LoadConsumptionView(
        UUID consumptionId,
        BigDecimal amount,
        CurrencyEnum currency,
        LocalDateTime consumptionDate,
        LocalDateTime consumptionApprobationDate,
        String cardId,
        String sellerName) {
}
