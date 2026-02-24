package com.bank.credit_card.domain.card.vo;

import com.bank.credit_card.domain.balance.vo.Amount;
import com.bank.credit_card.domain.card.CardException;

import java.math.BigDecimal;

import static com.bank.credit_card.domain.card.vo.CreditErrorMessage.*;

public class Credit {
    private Amount crediticialTotal;
    private BigDecimal debtTax;

    public Amount getCrediticialTotal() {
        return crediticialTotal;
    }

    public void setCrediticialTotal(Amount crediticialTotal) {
        this.crediticialTotal = crediticialTotal;
    }

    public BigDecimal getDebtTax() {
        return debtTax;
    }

    public void setDebtTax(BigDecimal debtTax) {
        this.debtTax = debtTax;
    }

    public Credit(Amount crediticialTotal, BigDecimal debtTax) {
        this.crediticialTotal = crediticialTotal;
        this.debtTax = debtTax;
    }

    public static Credit create(Amount crediticialTotal, BigDecimal debtTax) {

        if (crediticialTotal == null) {
            throw new CardException(CREDITICIAL_TOTAL_REQUIRED);
        }

        if (debtTax == null) {
            throw new CardException(DEBT_TAX_REQUIRED);
        }

        if (debtTax.compareTo(BigDecimal.ZERO) < 0) {
            throw new CardException(DEBT_TAX_NEGATIVE);
        }

        return new Credit(crediticialTotal, debtTax);
    }
}
