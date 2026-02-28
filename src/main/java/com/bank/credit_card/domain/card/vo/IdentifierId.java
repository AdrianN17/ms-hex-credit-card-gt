package com.bank.credit_card.domain.card.vo;

import java.util.UUID;

import static com.bank.credit_card.domain.card.vo.IdentifierIdErrorMessage.IDENTIFIER_ID_CANNOT_BE_NULL;
import static com.bank.credit_card.domain.util.Validation.isNotNull;

public class IdentifierId {
    private final UUID value;

    public IdentifierId(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    public static IdentifierId create(UUID value) {
        isNotNull(value, new IdentifierIdException(IDENTIFIER_ID_CANNOT_BE_NULL));
        return new IdentifierId(value);
    }

    public static IdentifierId create() {
        return new IdentifierId(UUID.randomUUID());
    }
}
