package com.bank.credit_card.domain.benefit;

public interface BenefitErrorMessage {
    String CATEGORY_NOT_NULL = "Categoria no debe ser nula";
    String POINT_NOT_NULL = "Puntos no debe ser nulo";
    String NOT_ENOUGH_POINTS = "No tienes suficientes puntos para canjear";
    String AMOUNT_NOT_NULL = "Amount cannot be null";
    String PAYMENT_NOT_NULL = "Payment cannot be null";
    String CARD_ID_NOT_NULL = "Card ID cannot be null";
    String DISCUOUNT_POLICY_NOT_NULL = "Discount policy cannot be null";

    String ID_CANNOT_BE_NULL = "ID cannot be null";

}
