package com.bank.credit_card.domain.consumption;

public interface ConsumptionErrorMessage {
    String CONSUMPTION_AMOUNT_CANNOT_BE_NULL = "The consumption amount cannot be null.";
    String CONSUMPTION_DATE_CANNOT_BE_NULL = "The consumption date cannot be null.";
    String IDENTIFIER_ID_CANNOT_BE_NULL = "The identifier ID cannot be null.";
    String CARD_ID_NOT_NULL = "El identificador de la tarjeta del pay no puede ser nulo";
    String CONSUMPTION_IS_STILL_IN_APPROBATION = "The consumption is still in approbation.";
    String TAX_AMOUNT_CANNOT_BE_NULL = "The tax amount cannot be null.";
    String QUANTITY_CANNOT_BE_NULL = "The quantity cannot be null.";
    String SELLER_NAME_CANNOT_BE_NULL = "The seller name cannot be null.";
    String SELLER_NAME_CANNOT_BE_EMPTY = "The seller name cannot be empty.";
}
