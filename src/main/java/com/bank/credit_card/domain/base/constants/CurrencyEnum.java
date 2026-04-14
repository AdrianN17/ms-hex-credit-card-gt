package com.bank.credit_card.domain.base.constants;

import java.util.Arrays;
import java.util.Optional;

public enum CurrencyEnum {
    PEN("PEN", 1),
    USD("USD", 2);

    private final String code;
    private final int value;

    CurrencyEnum(String code, int value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    public static Optional<CurrencyEnum> ofValue(Integer value) {
        if (value == null) return Optional.empty();
        return Arrays.stream(values())
                .filter(c -> c.value == value)
                .findFirst();
    }
}
