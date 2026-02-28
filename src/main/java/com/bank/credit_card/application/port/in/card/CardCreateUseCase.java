package com.bank.credit_card.application.port.in.card;

import com.bank.credit_card.domain.card.Card;

@FunctionalInterface
public interface CardCreateUseCase {
    void createCard(Card card);
}
