package com.bank.credit_card.domain.card.vo;

import static com.bank.credit_card.domain.card.vo.CardIdErrorMessage.CARD_ID_CANNOT_BE_NULL;
import static com.bank.credit_card.domain.util.Validation.isNotNull;

public class CardId {
    private final Long value;

    public CardId(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public static CardId create(Long value) {
        isNotNull(value, new CardIdException(CARD_ID_CANNOT_BE_NULL));
        return new CardId(value);
    }

    public static CardId create() {
        return new CardId(-1L);
    }
}
