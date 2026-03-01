package com.bank.credit_card.domain.payment;

public enum ChannelPaymentEnum {
    WEB("WEB", 1),
    APP("APP", 2);

    private final String code;
    private final int value;

    ChannelPaymentEnum(String code, int value) {
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
