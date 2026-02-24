package com.bank.credit_card.domain.card;

public enum TypeCardEnum {
    VISA("VISA", 1),
    MASTERCARD("MASTERCARD", 2);

    private final String code;
    private final int value;

    TypeCardEnum(String code, int value) {
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
