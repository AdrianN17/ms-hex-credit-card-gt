package com.bank.credit_card.infraestructure.persistence.db.nosql.common.exception;

public interface PaymentErrorMessage {
    String NO_PAYMENTS_FOUND = "No payments found";
    String PAYMENT_NOT_SAVED = "Payment not saved";
    String PAYMENT_NOT_FOUND = "Payment not found";

    String INCORRECT_CURRENCY_VALUE = "Incorrect currency value";
    String INCORRECT_CHANNEL_VALUE = "Incorrect channel value";
    String INCORRECT_CATEGORY_VALUE = "Incorrect category value";
}
