package com.bank.credit_card.application.port.out.card.query;

import com.bank.credit_card.application.port.in.query.view.LoadCardBalanceBenefitView;

import java.util.Optional;

@FunctionalInterface
public interface LoadCardBalanceBenefitPort {
    Optional<LoadCardBalanceBenefitView> loadAll(Long cardId);
}
