package com.bank.credit_card.application.port.out.card.usecase;

import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.card.Card;

import java.util.Optional;

@FunctionalInterface
public interface LoadCardPort {
    Optional<Card> load(Long cardId, Currency currency);
}
