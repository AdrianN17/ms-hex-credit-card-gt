package com.bank.credit_card.domain.payment;

import com.bank.credit_card.domain.card.CardStatusEnum;

import java.util.Arrays;
import java.util.Optional;

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

    public static Optional<ChannelPaymentEnum> ofValue(Integer value) {
        if (value == null) return Optional.empty();
        return Arrays.stream(values())
                .filter(c -> c.value == value)
                .findFirst();
    }
}
