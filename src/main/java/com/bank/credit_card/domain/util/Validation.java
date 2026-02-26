package com.bank.credit_card.domain.util;

import static java.util.Objects.isNull;

public class Validation {
    public static <T, X extends RuntimeException> void isNotNull(
            T value,
            X exception
    ) {
        if (isNull(value)) {
            throw exception;
        }
    }

    public static <X extends RuntimeException> void isConditional(
            Boolean value,
            X exception
    ) {
        if (value) {
            throw exception;
        }
    }
}
