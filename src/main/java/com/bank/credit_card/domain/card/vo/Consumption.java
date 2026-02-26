package com.bank.credit_card.domain.card.vo;

import com.bank.credit_card.domain.base.vo.Amount;

import static com.bank.credit_card.domain.card.vo.ConsumptionErrorMessage.CONSUMPTION_AMOUNT_CANNOT_BE_NULL;
import static com.bank.credit_card.domain.util.Validation.isNotNull;

public class Consumption {
    private final Amount consumo;

    private Consumption(Amount consumo) {
        this.consumo = consumo;
    }

    public static Consumption create(Amount consumo) {
        isNotNull(consumo, new ConsumptionException(CONSUMPTION_AMOUNT_CANNOT_BE_NULL));

        return new Consumption(consumo);
    }

    public Amount getConsumo() {
        return consumo;
    }
}
