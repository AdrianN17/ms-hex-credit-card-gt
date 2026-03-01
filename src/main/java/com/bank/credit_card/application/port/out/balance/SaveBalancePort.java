package com.bank.credit_card.application.port.out.balance;

import com.bank.credit_card.domain.card.Balance;

import java.util.Optional;

@FunctionalInterface
public interface SaveBalancePort {
    Optional<Long> save(Balance balance);
}
