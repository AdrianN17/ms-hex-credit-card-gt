package com.bank.credit_card.domain.card.vo;

import static com.bank.credit_card.domain.card.vo.CardIdErrorMessage.CARD_ID_CANNOT_BE_NULL;
import static com.bank.credit_card.domain.util.Validation.isNotNull;

public class CardAccountId {
    private final Long value;

    public CardAccountId(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public static CardAccountId create(Long value) {
        isNotNull(value, new CardIdException(CARD_ID_CANNOT_BE_NULL));
        return new CardAccountId(value);
    }

    public static CardAccountId create() {
        return new CardAccountId(-1L);
    }
}
