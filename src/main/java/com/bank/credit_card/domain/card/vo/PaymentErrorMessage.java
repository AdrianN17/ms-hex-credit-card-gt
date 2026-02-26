package com.bank.credit_card.domain.card.vo;

public interface PaymentErrorMessage {
    String PAYMENT_DAY_NOT_NULL = "El dia de pago no puede ser nulo";
    String PAYMENT_CATEGORY_NOT_NULL = "La categoria del pago no puede ser nula";
    String PAYMENT_AMOUNT_NOT_NULL = "El monto del pago no puede ser nulo";
    String PAYMENT_AMOUNT_NOT_ZERO = "El monto del pago no puede ser cero";
}
