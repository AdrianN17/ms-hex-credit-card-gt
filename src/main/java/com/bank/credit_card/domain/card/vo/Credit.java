package com.bank.credit_card.domain.card.vo;

import com.bank.credit_card.domain.base.vo.Amount;

import java.math.BigDecimal;

import static com.bank.credit_card.domain.card.vo.CreditErrorMessage.*;
import static com.bank.credit_card.domain.util.Validation.isConditional;
import static com.bank.credit_card.domain.util.Validation.isNotNull;

public class Credit {
    private final Amount creditTotal;
    private final BigDecimal debtTax;

    public Amount getCreditTotal() {
        return creditTotal;
    }

    public BigDecimal getDebtTax() {
        return debtTax;
    }

    private Credit(Amount creditTotal, BigDecimal debtTax) {
        this.creditTotal = creditTotal;
        this.debtTax = debtTax;
    }

    public static Credit create(Amount creditTotal, BigDecimal debtTax) {

        isNotNull(creditTotal, new CreditException(CREDITICIAL_TOTAL_REQUIRED));
        isNotNull(debtTax, new CreditException(DEBT_TAX_REQUIRED));
        isConditional(debtTax.compareTo(BigDecimal.ZERO) < 0, new CreditException(DEBT_TAX_NEGATIVE));

        return new Credit(creditTotal, debtTax);
    }
}
