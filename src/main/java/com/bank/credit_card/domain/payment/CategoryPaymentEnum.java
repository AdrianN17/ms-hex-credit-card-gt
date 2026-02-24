package com.bank.credit_card.domain.payment;

public enum CategoryPaymentEnum {

    MES("MES", 1),
    TOTAL("TOTAL", 2);

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
