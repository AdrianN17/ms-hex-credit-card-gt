package com.bank.credit_card.application.port.in.card;

@FunctionalInterface
public interface CardCloneUseCase {
    void cloneCard(Long cardId);
}
