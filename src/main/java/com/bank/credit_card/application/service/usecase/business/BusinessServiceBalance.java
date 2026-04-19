package com.bank.credit_card.application.service.usecase.business;

import com.bank.credit_card.domain.balance.Balance;
import com.bank.credit_card.domain.balance.BalanceId;

public interface BusinessServiceBalance {
    Balance get(Long cardId);

    BalanceId save(Balance balance);
}
