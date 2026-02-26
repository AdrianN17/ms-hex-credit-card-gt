package com.bank.credit_card.domain.base.vo;

import com.bank.credit_card.domain.card.BalanceException;

import java.math.BigDecimal;

import static com.bank.credit_card.domain.base.vo.AmountErrorMessage.AMOUNT_NEGATIVE;
import static com.bank.credit_card.domain.base.vo.AmountErrorMessage.AMOUNT_REQUIRED;
import static com.bank.credit_card.domain.util.Validation.isNotNull;

public class Amount {

    private final Currency currency;
    private final BigDecimal amount;

    private Amount(Currency currency,
                   BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public static Amount create(Currency currency,
                                BigDecimal amount)
            throws BalanceException {

        isNotNull(amount, new AmountException(AMOUNT_REQUIRED));

        if (amount.compareTo(BigDecimal.ZERO) < 0)
            throw new BalanceException(AMOUNT_NEGATIVE);

        return new Amount(currency, amount);
    }

    public Boolean estaVacio() {
        return this.getAmount()
                .compareTo(BigDecimal.ZERO) == 0;
    }

    public Boolean estaFaltando(Amount amount) {
        return getAmount()
                .compareTo(amount.getAmount()) > 0;
    }

    public Amount mas(Amount amount) {
        return Amount.create(getCurrency(),
                getAmount().add(convertir(this, amount).getAmount()));
    }

    public Amount menos(Amount amount) {

        return Amount.create(getCurrency(),
                this.amount.subtract(convertir(this, amount).getAmount()));
    }

    public static Amount convertir(Amount a, Amount b) {
        if (!a.getCurrency().sonIguales(b.getCurrency()))
            return Amount.create(Currency.create(a.getCurrency().getCurrency(), a.getCurrency().getExchangeRate()),
                    b.getAmount().multiply(a.getCurrency().getExchangeRate()));
        else
            return b;

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

    public Amount descuento(BigDecimal discount) {
        BigDecimal newAmount = this.getAmount().subtract(discount);
        return Amount.create(getCurrency(), newAmount);
    }

}
