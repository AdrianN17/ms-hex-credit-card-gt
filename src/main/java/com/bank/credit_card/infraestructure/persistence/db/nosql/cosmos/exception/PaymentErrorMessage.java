package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.exception;

public interface PaymentErrorMessage {
    String NO_PAYMENTS_FOUND = "No payments found";
    String PAYMENT_NOT_SAVED = "Payment not saved";
    String PAYMENT_NOT_FOUND = "Payment not found";
}
