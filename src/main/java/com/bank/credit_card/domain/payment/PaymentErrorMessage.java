package com.bank.credit_card.domain.payment;

public interface PaymentErrorMessage {
    String PAYMENT_DAY_NOT_NULL = "El dia de pay no puede ser nulo";
    String PAYMENT_CATEGORY_NOT_NULL = "La categoria del pay no puede ser nula";
    String PAYMENT_AMOUNT_NOT_NULL = "El monto del pay no puede ser nulo";
    String PAYMENT_AMOUNT_NOT_ZERO = "El monto del pay no puede ser cero";
    String IDENTIFIER_ID_NOT_NULL = "El identificador del pay no puede ser nulo";
    String CARD_ID_NOT_NULL = "El identificador de la tarjeta del pay no puede ser nulo";
    String PAYMENT_IS_STILL_IN_APPROBATION = "The payment is still in approbation.";
    String CHANGE_PAYMENT_NOT_NULL = "The change payment cannot be null.";
}
