package com.bank.credit_card.application.service.usecase.business;

import com.bank.credit_card.domain.balance.Balance;
import com.bank.credit_card.domain.balance.BalanceId;
import com.bank.credit_card.domain.balance.factory.BalanceType;

public interface BusinessServiceBalance {
    Balance get(Long cardId, BalanceType balanceType);

    BalanceId save(Balance balance);
}
