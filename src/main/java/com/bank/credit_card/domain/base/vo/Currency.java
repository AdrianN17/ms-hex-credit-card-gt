package com.bank.credit_card.domain.base.vo;

import com.bank.credit_card.domain.base.CurrencyEnum;

import java.math.BigDecimal;
import java.util.Objects;

import static com.bank.credit_card.domain.base.vo.CurrencyErrorMessage.CURRENCY_REQUIRED;
import static com.bank.credit_card.domain.base.vo.CurrencyErrorMessage.EXCHANGE_RATE_REQUIRED;
import static com.bank.credit_card.domain.util.Validation.isNotNull;

public class Currency {
    private final CurrencyEnum currency;
    private final BigDecimal exchangeRate;

    private Currency(CurrencyEnum currency, BigDecimal exchangeRate) {
        this.currency = currency;
        this.exchangeRate = exchangeRate;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public static Currency create(CurrencyEnum currency, BigDecimal exchangeRate) {

        isNotNull(currency, new CurrencyException(CURRENCY_REQUIRED));
        isNotNull(exchangeRate, new CurrencyException(EXCHANGE_RATE_REQUIRED));

        return new Currency(currency, exchangeRate);
    }

    public Boolean sonIguales(Currency currency) {
        return Objects.equals(getCurrency(), currency.getCurrency());
    }
}
