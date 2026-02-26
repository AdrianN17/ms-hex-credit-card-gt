package com.bank.credit_card.domain.card;

public interface BalanceErrorMessage {
    String PAYMENT_CANNOT_BE_NULL = "Payment cannot be null";
    String CONSUMPTION_CANNOT_BE_NULL = "Consumption cannot be null";
    String TOTAL_AMOUNT_CANNOT_BE_NULL = "Total amount cannot be null";
    String DATE_RANGE_CANNOT_BE_NULL = "Date range cannot be null";
    String POINT_CANNOT_BE_NULL = "Point cannot be null";
    String POINTS_CANNOT_USED_WITH_PREPAY = "Points cannot be used with prepay";
    String AVAILABLE_AMOUNT_CANNOT_BE_NULL = "Available amount cannot be null";
    String OLD_AMOUNT_CANNOT_BE_NULL = "Old amount cannot be null";
}
