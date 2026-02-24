package com.bank.credit_card.domain.card;

public enum CategoryCardEnum {
    NORMAL("NORMAL", 1),
    SILVER("SILVER", 2),
    GOLD("GOLD", 3),
    PLATINUM("PLATINUM", 4),
    BLACK("BLACK", 5),
    SIGNATURE("SIGNATURE", 6),
    INFINITY("INFINITY", 7);

    private final String code;
    private final int value;

    CategoryCardEnum(String code, int value) {
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
