package com.bank.credit_card.domain.consumption.vo;

import static com.bank.credit_card.domain.consumption.vo.SellerNameErrorMessage.SELLER_NAME_CANNOT_BE_EMPTY;
import static com.bank.credit_card.domain.consumption.vo.SellerNameErrorMessage.SELLER_NAME_CANNOT_BE_NULL;
import static com.bank.credit_card.domain.util.Validation.isNotConditional;
import static com.bank.credit_card.domain.util.Validation.isNotNull;

public class SellerName {
    private final String value;

    private SellerName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SellerName create(String value) {
        isNotNull(value, new SellerNameException(SELLER_NAME_CANNOT_BE_NULL));
        isNotConditional(value.isBlank(), new SellerNameException(SELLER_NAME_CANNOT_BE_EMPTY));
        return new SellerName(value);
    }
}
