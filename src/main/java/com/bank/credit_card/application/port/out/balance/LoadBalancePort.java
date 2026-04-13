package com.bank.credit_card.application.port.out.balance;

import com.bank.credit_card.domain.balance.Balance;
import com.bank.credit_card.domain.base.vo.Currency;

import java.util.Optional;

@FunctionalInterface
public interface LoadBalancePort {
    Optional<Balance> load(Long cardId, Currency currency);
}
