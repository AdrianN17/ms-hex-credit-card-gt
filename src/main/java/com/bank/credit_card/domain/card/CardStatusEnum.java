package com.bank.credit_card.domain.card;

public enum CardStatusEnum {
    OPERATIVE("OPERATIVA", 1),
    OVERCHARGE("SOBREGIRADA", 2),
    IN_DEBT("DEUDOR", 3);

    private final String code;
    private final int value;

    CardStatusEnum(String code, int value) {
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
