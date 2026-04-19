package com.bank.credit_card.domain.card;

public interface CardErrorMessage {
    String TYPE_CARD_CANNOT_BE_NULL = "Type card cannot be null";
    String CATEGORY_CARD_CANNOT_BE_NULL = "Category card cannot be null";
    String CREDIT_CANNOT_BE_NULL = "Credit cannot be null";
    String CREDIT_TOTAL_CANNOT_BE_NULL = "Credit total cannot be null";
    String DEBT_TAX_CANNOT_BE_NULL = "Debt tax cannot be null";
    String CURRENCY_CANNOT_BE_NULL = "Currency cannot be null";
    String EXCHANGE_RATE_CANNOT_BE_NULL = "Exchange rate cannot be null";
    String PAYMENT_DAY_CANNOT_BE_NULL = "Payment paymentDay cannot be null";

    String ID_CANNOT_BE_NULL = "ID cannot be null";
    String CARD_STATUS_CANNOT_BE_NULL = "Card status cannot be null";

    String IN_DEBT_CARD = "No se puede pagar una tarjeta que esta en deuda";

}
