package com.bank.credit_card.domain.card;

public interface CardErrorMessage {
    String AMOUNT_EXCEED_CREDIT_LIMIT = "El monto de consumo excede la linea de credito";
    String IN_DEBT_CARD = "No se puede pagar una tarjeta que no esta en deuda";

    String PAYMENT_CATEGORY_NOT_SAME_AS_PAYMENT = "La categoria de pago no coincide con el monto a pagar";
    String PAYMENT_CATEGORY_EXCEED_LIKE = "El monto de pago excede en ";
}
