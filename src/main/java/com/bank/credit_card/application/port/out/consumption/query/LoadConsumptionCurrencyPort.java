package com.bank.credit_card.application.port.out.consumption.query;

import com.bank.credit_card.domain.base.CurrencyEnum;

import java.util.Optional;
import java.util.UUID;

public interface LoadConsumptionCurrencyPort {
    Optional<CurrencyEnum> load(UUID consumptionId);
}
