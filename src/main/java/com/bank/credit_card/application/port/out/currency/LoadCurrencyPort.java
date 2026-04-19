package com.bank.credit_card.application.port.out.currency;

import com.bank.credit_card.domain.base.enums.CurrencyEnum;
import com.bank.credit_card.domain.base.vo.Currency;

import java.util.Optional;

@FunctionalInterface
public interface LoadCurrencyPort {
    Optional<Currency> load(CurrencyEnum currencyEnum);
}
