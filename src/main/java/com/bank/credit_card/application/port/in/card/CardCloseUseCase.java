package com.bank.credit_card.application.port.in.card;

@FunctionalInterface
public interface CardCloseUseCase {
    void closeCard(Long cardId);
}
