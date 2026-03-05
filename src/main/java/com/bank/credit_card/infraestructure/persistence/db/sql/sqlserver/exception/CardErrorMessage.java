package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.exception;

public interface CardErrorMessage {
    String NO_CARD_AND_BALANCE_AND_BENEFIT_FOUND = "No card with children found";
    String CARD_NOT_SAVED = "Card not saved";
    String CARD_NOT_FOUND = "Card not found";
    String CARD_ACCOUNT_NOT_SAVED = "Card account not saved";

    String INCORRECT_CURRENCY_VALUE = "Incorrect currency value";
    String INCORRECT_CATEGORY_VALUE = "Incorrect category value";
    String INCORRECT_TYPE_CARD_VALUE = "Incorrect type card value";
    String INCORRECT_CARD_STATUS_VALUE = "Incorrect card status value";
}
