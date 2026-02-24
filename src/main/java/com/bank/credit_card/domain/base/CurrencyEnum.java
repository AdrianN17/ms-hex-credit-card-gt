package com.bank.credit_card.domain.base;

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
}
