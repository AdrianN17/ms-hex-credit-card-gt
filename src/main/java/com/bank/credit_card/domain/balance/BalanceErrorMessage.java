package com.bank.credit_card.domain.balance;

public interface BalanceErrorMessage {
    String PAYMENT_CANNOT_BE_NULL = "Payment cannot be null";
    String TOTAL_AMOUNT_CANNOT_BE_NULL = "Total amount cannot be null";
    String DATE_RANGE_CANNOT_BE_NULL = "Date range cannot be null";
    String AVAILABLE_AMOUNT_CANNOT_BE_NULL = "Available amount cannot be null";
    String OLD_AMOUNT_CANNOT_BE_NULL = "Old amount cannot be null";
    String CARD_ID_CANNOT_BE_NULL = "Card ID cannot be null";
    String CURRENCY_CANNOT_BE_NULL = "Currency cannot be null";
    String EXCHANGE_RATE_CANNOT_BE_NULL = "Exchange rate cannot be null";

    String ID_CANNOT_BE_NULL = "ID cannot be null";
    String AMOUNT_EXCEED_CREDIT_LIMIT = "El monto de consumo excede la linea de credito";
    String PAYMENT_CATEGORY_EXCEED_LIKE = "El monto de pay excede en ";


}
