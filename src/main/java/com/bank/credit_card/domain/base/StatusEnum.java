package com.bank.credit_card.domain.base;

public enum StatusEnum {
    INACTIVE("INACTIVE", 0),
    ACTIVE("ACTIVE", 1);

    private final String code;
    private final int value;

    StatusEnum(String code, int value) {
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