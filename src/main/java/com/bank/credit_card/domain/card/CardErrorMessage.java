package com.bank.credit_card.domain.card;

public interface CardErrorMessage {
    String AMOUNT_EXCEED_CREDIT_LIMIT = "El monto de consumo excede la linea de credito";
    String IN_DEBT_CARD = "No se puede pay una tarjeta que no esta en deuda";

    String PAYMENT_CATEGORY_NOT_SAME_AS_PAYMENT = "La categoria de pay no coincide con el monto a pay";
    String PAYMENT_CATEGORY_EXCEED_LIKE = "El monto de pay excede en ";

    String TYPE_CARD_CANNOT_BE_NULL = "Type card cannot be null";
    String CATEGORY_CARD_CANNOT_BE_NULL = "Category card cannot be null";
    String CREDIT_CANNOT_BE_NULL = "Credit cannot be null";
    String BALANCE_CANNOT_BE_NULL = "Balance cannot be null";
    String BENEFIT_CANNOT_BE_NULL = "Benefit cannot be null";
    String IDENTIFIER_ID_CANNOT_BE_NULL = "IdentifierId cannot be null";

}
