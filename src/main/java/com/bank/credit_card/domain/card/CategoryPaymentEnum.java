package com.bank.credit_card.domain.card;

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
}
