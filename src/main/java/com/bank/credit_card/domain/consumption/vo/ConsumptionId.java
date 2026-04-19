package com.bank.credit_card.domain.consumption.vo;

import java.util.UUID;

import static com.bank.credit_card.domain.consumption.vo.ConsumptionIdErrorMessage.CONSUMPTION_ID_CANNOT_BE_NULL;
import static com.bank.credit_card.domain.util.Validation.isNotNull;

public class ConsumptionId {
    private final UUID value;

    public ConsumptionId(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    public static ConsumptionId create(UUID value) {
        isNotNull(value, new ConsumptionIdException(CONSUMPTION_ID_CANNOT_BE_NULL));
        return new ConsumptionId(value);
    }
}
