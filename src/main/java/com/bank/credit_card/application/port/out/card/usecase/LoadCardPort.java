package com.bank.credit_card.application.port.out.card.usecase;

import com.bank.credit_card.application.port.in.query.view.LoadCardView;

import java.util.Optional;

@FunctionalInterface
public interface LoadCardPort {
    Optional<LoadCardView> load(Long cardId);
}
