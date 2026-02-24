package com.bank.credit_card.domain.balance;

import com.bank.credit_card.domain.balance.vo.Amount;
import com.bank.credit_card.domain.balance.vo.Currency;
import com.bank.credit_card.domain.balance.vo.DateRange;
import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.domain.base.GenericDomain;
import com.bank.credit_card.domain.exception.DomainException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.util.Objects.requireNonNullElse;

public class Balance extends GenericDomain {
    private Amount total;
    private Amount old;
    private DateRange dateRange;
    private Amount available;

    public Balance(Long id, Amount old, Amount total, DateRange dateRange, Amount available) throws DomainException {
        super(id);
        this.total = total;
        this.old = old;
        this.dateRange = dateRange;
        this.available = available;
    }

    public Balance(Amount total, Amount old, DateRange dateRange, Amount available) {
        super(null);
        this.total = total;
        this.old = old;
        this.dateRange = dateRange;
        this.available = available;
    }

    public static Balance create(BigDecimal totalAmount,
                                 BigDecimal oldAmount,
                                 CurrencyEnum currency,
                                 BigDecimal exchangeRate,
                                 LocalDate startDate,
                                 LocalDate endDate,
                                 BigDecimal available) {
        return new Balance(
                Amount.create(Currency.create(currency, exchangeRate), totalAmount),
                Amount.create(Currency.create(currency, exchangeRate), requireNonNullElse(oldAmount, totalAmount)),
                DateRange.create(startDate, endDate),
                Amount.create(Currency.create(currency, exchangeRate), available)
        );
    }

    public static Balance create(Long id,
                                 BigDecimal totalAmount,
                                 BigDecimal oldAmount,
                                 CurrencyEnum currency,
                                 BigDecimal exchangeRate,
                                 LocalDate startDate,
                                 LocalDate endDate,
                                 BigDecimal available) {
        return new Balance(
                id,
                Amount.create(Currency.create(currency, exchangeRate), totalAmount),
                Amount.create(Currency.create(currency, exchangeRate), oldAmount),
                DateRange.create(startDate, endDate),
                Amount.create(Currency.create(currency, exchangeRate), available)
        );
    }

    public Balance nuevo(Short intervalo, Amount pagos, Amount consumos) {

        return new Balance(
                Amount.create(this.total.getCurrency(), this.total.getAmount()),
                Amount.create(this.old.getCurrency(), this.old.getAmount()),
                DateRange.create(intervalo),
                disponible(pagos, consumos)
        );
    }

    public void disponiblePago(LocalDate fecha) {
        dateRange.ensureWithinRange(fecha);
    }

    public Amount disponible(Amount pagos, Amount consumos) {
        return pagos.mas(old).menos(consumos);
    }


    @Override
    public boolean valid() throws DomainException {
        return false;
    }

    public Amount getTotal() {
        return total;
    }

    public void setTotal(Amount total) {
        this.total = total;
    }

    public Amount getOld() {
        return old;
    }

    public void setOld(Amount old) {
        this.old = old;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    public Amount getAvailable() {
        return available;
    }

    public void setAvailable(Amount available) {
        this.available = available;
    }
}
