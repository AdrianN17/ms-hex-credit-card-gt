package com.bank.credit_card.domain.balance.vo;

import com.bank.credit_card.domain.balance.BalanceException;

import java.math.BigDecimal;

public class Amount {

    private Currency currency;
    private BigDecimal amount;

    public Amount(Currency currency,
                  BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public static Amount create(Currency currency,
                                BigDecimal amount)
            throws BalanceException {

        if (amount == null) {
            throw new BalanceException(
                    MoneyErrorMessage.AMOUNT_REQUIRED
            );
        }

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new BalanceException(
                    MoneyErrorMessage.AMOUNT_NEGATIVE
            );
        }

        return new Amount(currency, amount);
    }

    public Boolean estaFaltando(Amount amount) {
        return this.getAmount().compareTo(amount.getAmount()) > 0;
    }

    public Amount mas(Amount amount) {
        convertir(amount.getCurrency());
        return Amount.create(getCurrency(), this.amount.add(amount.getAmount()));
    }

    public Amount menos(Amount amount) {
        convertir(amount.getCurrency());
        return Amount.create(getCurrency(), this.amount.subtract(amount.getAmount()));
    }

    public void convertir(Currency currency) {
        if (this.getCurrency().getCurrency() != currency.getCurrency()) {
            setCurrency(currency);
        }
    }

    public Boolean estaSobrando(Amount amount) {
        return this.getAmount().compareTo(amount.getAmount()) < 0;
    }

    public Boolean esIgual(Amount amount) {
        return this.getAmount().compareTo(amount.getAmount()) == 0;
    }

    public Amount deuda(BigDecimal debtTax) {
        BigDecimal newAmount = this.getAmount().add(debtTax);
        return Amount.create(getCurrency(), newAmount);
    }

    public Amount fraccionar(Integer quantity, BigDecimal debtTax) {

        return Amount.create(getCurrency(), amount.divide(BigDecimal.valueOf(quantity))
                .add(amount.multiply(debtTax)));
    }


    public String toString() {
        return amount.toString() + " " + currency.getCurrency().getCode();
    }

}
