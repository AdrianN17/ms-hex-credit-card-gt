package com.bank.credit_card.application.port.out.balance;

import com.bank.credit_card.application.port.in.query.view.LoadBalanceView;

import java.util.Optional;

@FunctionalInterface
public interface LoadBalancePort {
    Optional<LoadBalanceView> load(Long balanceId);
}
