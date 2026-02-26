package com.bank.credit_card.domain.benefit;

public interface BenefitErrorMessage {
    String CATEGORY_NOT_NULL = "Categoria no debe ser nula";
    String POINT_NOT_NULL = "Puntos no debe ser nulo";
    String NOT_ENOUGH_POINTS = "No tienes suficientes puntos para canjear";
    String HAS_DISCOUNT_NOT_NULL = "Has discount flag cannot be null";
    String MULTIPLIER_POINTS_NOT_NULL = "Multiplier points cannot be null";
    String AMOUNT_NOT_NULL = "Amount cannot be null";
    String PAYMENT_NOT_NULL = "Payment cannot be null";

}
