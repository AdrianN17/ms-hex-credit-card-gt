package com.bank.credit_card.application.service.usecase.business;

import com.bank.credit_card.domain.card.Card;
import com.bank.credit_card.domain.card.vo.CardId;

public interface BusinessServiceCard {
    Card get(Long cardId);

    CardId save(Card card);
}
