package com.bank.credit_card.domain.balance;

import com.bank.credit_card.domain.base.vo.Amount;

public interface BalanceOperable extends Balance {

    Boolean isOvercharged();

    void apply(Amount amount);

    void cancel(Amount amount);
}

