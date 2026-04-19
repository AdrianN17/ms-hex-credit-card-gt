package com.bank.credit_card.domain.card.vo;


import static com.bank.credit_card.domain.card.vo.PaymentDayErrorMessage.PAYMENT_DAY_CANNOT_BE_NULL;
import static com.bank.credit_card.domain.util.Validation.isNotNull;

public class PaymentDay {
    private final Short paymentDay;

    public PaymentDay(Short paymentDay) {
        this.paymentDay = paymentDay;
    }

    public Short getValue() {
        return paymentDay;
    }

    public static PaymentDay create(Short paymentDay) {
        isNotNull(paymentDay, new PaymentDayException(PAYMENT_DAY_CANNOT_BE_NULL));
        return new PaymentDay(paymentDay);
    }
}
