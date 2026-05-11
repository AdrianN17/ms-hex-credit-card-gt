package com.bank.credit_card.domain.payment;

import java.util.Arrays;
import java.util.Optional;

public enum CategoryPaymentEnum {

    NORMAL("NORMAL", 1),
    TOTAL("TOTAL", 2),
    MINIMO("MINIMO", 3),
    ADELANTADO("ADELANTADO", 4);

    private final String code;
    private final int value;

    CategoryPaymentEnum(String code, int value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    public static Optional<CategoryPaymentEnum> ofValue(Integer value) {
        if (value == null) return Optional.empty();
        return Arrays.stream(values())
                .filter(c -> c.value == value)
                .findFirst();
    }
}
