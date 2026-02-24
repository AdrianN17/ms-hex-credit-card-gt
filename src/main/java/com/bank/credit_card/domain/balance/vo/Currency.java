package com.bank.credit_card.domain.balance.vo;

import com.bank.credit_card.domain.balance.BalanceException;
import com.bank.credit_card.domain.base.CurrencyEnum;

import java.math.BigDecimal;

import static com.bank.credit_card.domain.balance.vo.CurrencyErrorMessage.CURRENCY_REQUIRED;
import static com.bank.credit_card.domain.balance.vo.CurrencyErrorMessage.EXCHANGE_RATE_REQUIRED;

public class Currency {
    private CurrencyEnum currency;
    private BigDecimal exchangeRate;

    public Currency(CurrencyEnum currency, BigDecimal exchangeRate) {
        this.currency = currency;
        this.exchangeRate = exchangeRate;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public static Currency create(CurrencyEnum currency, BigDecimal exchangeRate) {

        if (currency == null) {
            throw new BalanceException(
                    CURRENCY_REQUIRED
            );
        }

        if (exchangeRate == null) {
            throw new BalanceException(
                    EXCHANGE_RATE_REQUIRED
            );
        }

        return new Currency(currency, exchangeRate);
    }
}
