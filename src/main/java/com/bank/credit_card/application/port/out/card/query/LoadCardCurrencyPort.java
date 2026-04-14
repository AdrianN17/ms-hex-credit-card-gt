package com.bank.credit_card.application.port.out.card.query;

import com.bank.credit_card.domain.base.constants.CurrencyEnum;

import java.util.Optional;

public interface LoadCardCurrencyPort {
    Optional<CurrencyEnum> load(Long cardId);
}
